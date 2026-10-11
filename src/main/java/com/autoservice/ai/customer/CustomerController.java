
package com.autoservice.ai.customer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerRegistrationResponse registerCustomer(
            @Valid @RequestBody CustomerRegistrationRequest request
    ) {
        return customerService.registerCustomer(request);
    }
}