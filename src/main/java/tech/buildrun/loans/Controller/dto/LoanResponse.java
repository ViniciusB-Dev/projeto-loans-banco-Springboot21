package tech.buildrun.loans.Controller.dto;

import tech.buildrun.loans.domain.LoanType;

public record LoanResponse(LoanType type, Double interestRate) {
}