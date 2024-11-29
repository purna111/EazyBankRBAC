package com.eazybytes.controller;

import com.eazybytes.model.Customer;
import com.eazybytes.model.Loans;
import com.eazybytes.repository.CustomerRepository;
import com.eazybytes.repository.LoanRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(
        name = "REST API for Loans Details in Eazybank",
        description = "To Get Loans Details of Valid user who is having role of USER"
)
@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    @Operation(
            summary = "Fetch Loans Details REST API",
            description = "REST API to fetch Loans details based on a Valid User Email that exist in DB"
    )
    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoanDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(optionalCustomer.get().getId());
            if (loans != null) {
                return loans;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
