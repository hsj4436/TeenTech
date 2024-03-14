package com.ssafy.teentech.transaction.service;

import com.ssafy.teentech.account.domain.Account;
import com.ssafy.teentech.account.repository.AccountRepository;
import com.ssafy.teentech.common.error.ErrorCode;
import com.ssafy.teentech.common.error.exception.AccountException;
import com.ssafy.teentech.common.error.exception.TransactionException;
import com.ssafy.teentech.transaction.domain.Transaction;
import com.ssafy.teentech.transaction.dto.TransactionType;
import com.ssafy.teentech.transaction.dto.request.AutoTransactionRequestDto;
import com.ssafy.teentech.transaction.dto.request.TransactionListRequestDto;
import com.ssafy.teentech.transaction.dto.request.TransactionRequestDto;
import com.ssafy.teentech.transaction.dto.response.TransactionListResponseDto;
import com.ssafy.teentech.transaction.dto.response.TransactionResponseDto;
import com.ssafy.teentech.transaction.repository.TransactionRepository;

import java.util.Objects;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public void executeTransaction(TransactionRequestDto transactionRequestDto) {
        if (transactionRequestDto.getAmount() < 1) {
            throw new TransactionException(ErrorCode.INVALID_TRANSFER_AMOUNT);
        }

        String withdrawAccountNumber = transactionRequestDto.getWithdrawAccountNumber();
        String depositAccountNumber = transactionRequestDto.getDepositAccountNumber();

        if (withdrawAccountNumber.compareTo(depositAccountNumber) == -1) {
            Account withdrawAccount = accountRepository.findByAccountNumberForUpdate(
                            withdrawAccountNumber)
                    .orElseThrow(() -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

            withdrawAccount.checkOwner(transactionRequestDto.getWithdrawAccountId());
            withdrawAccount.checkPassword(transactionRequestDto.getWithdrawAccountPassword());

            Account depositAccount = accountRepository.findByAccountNumberForUpdate(
                            depositAccountNumber)
                    .orElseThrow(() -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

            withdrawAccount.withdraw(transactionRequestDto.getAmount());
            depositAccount.deposit(transactionRequestDto.getAmount());

            Transaction transaction = Transaction.builder().withdrawAccount(withdrawAccount)
                    .balanceAfterWithdraw(withdrawAccount.getBalance()).depositAccount(depositAccount)
                    .balanceAfterDeposit(depositAccount.getBalance())
                    .transferAmount(transactionRequestDto.getAmount())
                    .content(transactionRequestDto.getContent()).build();
            transactionRepository.save(transaction);
        } else {
            Account depositAccount = accountRepository.findByAccountNumberForUpdate(
                            depositAccountNumber)
                    .orElseThrow(() -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

            Account withdrawAccount = accountRepository.findByAccountNumberForUpdate(
                            withdrawAccountNumber)
                    .orElseThrow(() -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

            withdrawAccount.checkOwner(transactionRequestDto.getWithdrawAccountId());
            withdrawAccount.checkPassword(transactionRequestDto.getWithdrawAccountPassword());

            withdrawAccount.withdraw(transactionRequestDto.getAmount());
            depositAccount.deposit(transactionRequestDto.getAmount());

            Transaction transaction = Transaction.builder().withdrawAccount(withdrawAccount)
                    .balanceAfterWithdraw(withdrawAccount.getBalance()).depositAccount(depositAccount)
                    .balanceAfterDeposit(depositAccount.getBalance())
                    .transferAmount(transactionRequestDto.getAmount())
                    .content(transactionRequestDto.getContent()).build();
            transactionRepository.save(transaction);
        }
    }

    public void executeAutoTransaction(AutoTransactionRequestDto autoTransactionRequestDto) {
        if (autoTransactionRequestDto.getAmount() < 1) {
            throw new TransactionException(ErrorCode.INVALID_TRANSFER_AMOUNT);
        }

        String withdrawAccountNumber = autoTransactionRequestDto.getWithdrawAccountNumber();
        String depositAccountNumber = autoTransactionRequestDto.getDepositAccountNumber();

        if (withdrawAccountNumber.compareTo(depositAccountNumber) == -1) {
            Account withdrawAccount = accountRepository.findByAccountNumberForUpdate(
                            withdrawAccountNumber)
                    .orElseThrow(() -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

            withdrawAccount.checkOwner(autoTransactionRequestDto.getWithdrawAccountId());

            Account depositAccount = accountRepository.findByAccountNumberForUpdate(
                            depositAccountNumber)
                    .orElseThrow(() -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

            withdrawAccount.withdraw(autoTransactionRequestDto.getAmount());
            depositAccount.deposit(autoTransactionRequestDto.getAmount());

            Transaction transaction = Transaction.builder().withdrawAccount(withdrawAccount)
                    .balanceAfterWithdraw(withdrawAccount.getBalance()).depositAccount(depositAccount)
                    .balanceAfterDeposit(depositAccount.getBalance())
                    .transferAmount(autoTransactionRequestDto.getAmount())
                    .content(autoTransactionRequestDto.getContent()).build();
            transactionRepository.save(transaction);
        } else {
            Account depositAccount = accountRepository.findByAccountNumberForUpdate(
                            depositAccountNumber)
                    .orElseThrow(() -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

            Account withdrawAccount = accountRepository.findByAccountNumberForUpdate(
                            withdrawAccountNumber)
                    .orElseThrow(() -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

            withdrawAccount.checkOwner(autoTransactionRequestDto.getWithdrawAccountId());

            withdrawAccount.withdraw(autoTransactionRequestDto.getAmount());
            depositAccount.deposit(autoTransactionRequestDto.getAmount());

            Transaction transaction = Transaction.builder().withdrawAccount(withdrawAccount)
                    .balanceAfterWithdraw(withdrawAccount.getBalance()).depositAccount(depositAccount)
                    .balanceAfterDeposit(depositAccount.getBalance())
                    .transferAmount(autoTransactionRequestDto.getAmount())
                    .content(autoTransactionRequestDto.getContent()).build();
            transactionRepository.save(transaction);
        }
    }

    public TransactionListResponseDto getTransactions(
        TransactionListRequestDto transactionListRequestDto) {
        Account account = accountRepository.findByAccountNumber(transactionListRequestDto.getAccountNumber())
            .orElseThrow(() -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));
        account.checkOwner(transactionListRequestDto.getUserId());

        TransactionListResponseDto transactionListResponseDto = new TransactionListResponseDto(
            transactionRepository.findAllByWithdrawAccountOrDepositAccountAndTransactionIdGreaterThan(account, new Long(transactionListRequestDto.getIndex())).stream()
                .map(t -> {
                    if (account.equals(t.getDepositAccount())) {
                        if (Objects.isNull(t.getWithdrawAccount())) {
                            return new TransactionResponseDto(t.getTransactionId(),
                                    TransactionType.DEPOSIT, null,
                                    t.getBalanceAfterDeposit(), t.getTransferAmount(), t.getContent(),
                                    t.getCreatedDateTime());
                        } else {
                            return new TransactionResponseDto(t.getTransactionId(),
                                    TransactionType.DEPOSIT, t.getWithdrawAccount().getUserName(),
                                    t.getBalanceAfterDeposit(), t.getTransferAmount(), t.getContent(),
                                    t.getCreatedDateTime());
                        }
                    } else {
                        if (Objects.isNull(t.getDepositAccount())) {
                            return new TransactionResponseDto(t.getTransactionId(),
                                    TransactionType.WITHDRAW, null,
                                    t.getBalanceAfterWithdraw(), t.getTransferAmount(), t.getContent(),
                                    t.getCreatedDateTime());
                        } else {
                            return new TransactionResponseDto(t.getTransactionId(),
                                    TransactionType.WITHDRAW, t.getDepositAccount().getUserName(),
                                    t.getBalanceAfterWithdraw(), t.getTransferAmount(), t.getContent(),
                                    t.getCreatedDateTime());
                        }
                    }
                }).collect(Collectors.toList()));

        return transactionListResponseDto;
    }
}
