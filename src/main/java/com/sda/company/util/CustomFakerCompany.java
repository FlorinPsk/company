package com.sda.company.util;

import com.github.javafaker.Faker;
import com.sda.company.model.Company;
import java.util.ArrayList;
import java.util.List;

public class CustomFakerCompany {

    public List<Company> generateCompanies() {
        List<Company> companies = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            Company company = new Company();
            company.setName(faker.company().name());
            company.setAddress(faker.address().fullAddress());
            company.setEmail(faker.bothify("?????##@company.com"));
            company.setRegistrationNumber(faker.number().randomNumber(5, true));

            companies.add(company);
        }

        return companies;
    }
}
