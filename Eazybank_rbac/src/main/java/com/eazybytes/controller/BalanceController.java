package com.eazybytes.controller;

import com.eazybytes.model.AccountTransactions;
import com.eazybytes.model.Accounts;
import com.eazybytes.model.Customer;
import com.eazybytes.repository.AccountTransactionsRepository;
import com.eazybytes.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(
        name = "REST API for Balance Details in Eazybank",
        description = "To Get Balance Details of Valid user who is having role of USER or ADMIN"
)
@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;
    private final CustomerRepository customerRepository;

    @Operation(
            summary = "Fetch Balance Details REST API",
            description = "REST API to fetch Balance details based on a Valid User Email that exist in DB"
    )
    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                    findByCustomerIdOrderByTransactionDtDesc(optionalCustomer.get().getId());
            if (accountTransactions != null) {
                return accountTransactions;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
