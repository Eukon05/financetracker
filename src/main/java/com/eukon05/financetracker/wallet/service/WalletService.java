package com.eukon05.financetracker.wallet.service;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.wallet.CurrencyConverter;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.WalletRepository;
import com.eukon05.financetracker.wallet.WalletStatisticRepository;
import com.eukon05.financetracker.wallet.dto.CreateWalletDTO;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import com.eukon05.financetracker.wallet.dto.WalletDTO;
import com.eukon05.financetracker.wallet.exception.WalletNameTakenException;
import com.eukon05.financetracker.wallet.exception.WalletNotFoundException;
import com.eukon05.financetracker.wallet.mapper.WalletModelMapper;
import com.eukon05.financetracker.wallet.projection.WalletStatisticProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletModelMapper mapper;
    private final WalletRepository repository;
    private final WalletStatisticRepository statisticRepository;
    private final CurrencyConverter converter;

    public void createWallet(String userId, CreateWalletDTO dto) {
        if (repository.existsByUserIdAndName(userId, dto.name())) {
            throw new WalletNameTakenException(dto.name());
        }

        repository.save(new Wallet(userId, dto.name(), dto.currency()));
    }

    public void deleteWallet(String userId, long walletID) {
        Wallet wallet = getWalletById(userId, walletID);
        repository.delete(wallet);
    }

    public void editWallet(String userId, long walletID, EditWalletDTO dto) {
        Wallet wallet = getWalletById(userId, walletID);

        Optional.ofNullable(dto.name()).ifPresent(name -> {
            if (repository.existsByUserIdAndName(userId, name)) {
                throw new WalletNameTakenException(dto.name());
            }
            wallet.setName(name);
        });

        Optional.ofNullable(dto.currency()).ifPresent(currency -> {
            for (Transaction transaction : wallet.getTransactions()) {
                transaction.setValue(converter.convert(wallet.getCurrency(), currency, transaction.getValue(), transaction.getCreatedAt()));
            }
            wallet.setCurrency(currency);
        });
    }

    public List<WalletDTO> getUserWalletDTOs(String userId) {
        return repository.getWalletsByUserId(userId).stream().map(mapper::mapWalletToWalletDTO).toList();
    }

    public WalletDTO getWalletDTOById(String userId, long walletID) {
        Wallet wallet = getWalletById(userId, walletID);
        return mapper.mapWalletToWalletDTO(wallet);
    }

    public Wallet getWalletById(String userId, long walletID) {
        return repository.getWalletByUserIdAndId(userId, walletID).orElseThrow(() -> new WalletNotFoundException(walletID));
    }

    public List<WalletStatisticProjection> getWalletStatisticsById(String userId, long walletID, Long categoryID) {
        Wallet wallet = getWalletById(userId, walletID);

        if (categoryID != null) {
            Optional<WalletStatisticProjection> projection = statisticRepository.getWalletStatisticsForCategory(wallet, categoryID);
            List<WalletStatisticProjection> list = Collections.emptyList();

            if (projection.isPresent())
                list = List.of(projection.get());

            return list;
        }

        return statisticRepository.getWalletStatistics(wallet);

    }
    
}
