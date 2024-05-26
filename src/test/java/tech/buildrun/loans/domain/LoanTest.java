package tech.buildrun.loans.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.buildrun.loans.domain.exception.LoanNotAvailableException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class LoanTest {
    @Mock
    private  Customer customer;

    @InjectMocks
    private  Loan loan;

    @Nested
    class isPersonlLoanAvailable{

        @Test
        void shouldBeAvaliableWhenWhenIncomeIsEqualOrLess3k() {

            doReturn(true).when(customer).isIncomeEqualOrLowerThan(3000.0);

            assertTrue(loan.isPersonalLoanAvailable());
        }

        @Test
        void shouldBeAvaliableWhenWhenIncomeIsBetween3kAnd5kAndAgeIsLessThan30AndLocationsIsSP() {

            doReturn(false).when(customer).isIncomeEqualOrLowerThan(3000.0);
            doReturn(true).when(customer).isIncomeBetween(3000.0, 5000.0);
            doReturn(true).when(customer).isAgeLowerThan(30);
            doReturn(true).when(customer).isFromLocation("SP");

            assertTrue(loan.isPersonalLoanAvailable());
        }
    }

    @Nested
    class isGuaranteedLoanAvailable{

        @Test
        void shouldBeAvaliableWhenWhenIncomeIsEqualOrLess3k() {

            doReturn(true).when(customer).isIncomeEqualOrLowerThan(3000.0);

            assertTrue(loan.isGuaranteedLoanAvailable());
        }

        @Test
        void shouldBeAvaliableWhenWhenIncomeIsBetween3kAnd5kAndAgeIsLessThan30AndLocationsIsSP() {

            doReturn(false).when(customer).isIncomeEqualOrLowerThan(3000.0);
            doReturn(true).when(customer).isIncomeBetween(3000.0, 5000.0);
            doReturn(true).when(customer).isAgeLowerThan(30);
            doReturn(true).when(customer).isFromLocation("SP");

            assertTrue(loan.isPersonalLoanAvailable());
        }
    }

    @Nested
    class isConsignorLoanAvailable{
        @Test
        void shouldBeAvaliableWhenWhenIncomeIsEqualOrGraterThan5k() {

            doReturn(true).when(customer).isIncomeEqualOrLowerThan(3000.0);

            assertTrue(loan.isGuaranteedLoanAvailable());
        }

        @Test
        void shouldNotBeAvaliableWhenWhenIncomeIsEqual4K() {

            doReturn(false).when(customer).isIncomeEqualOrLowerThan(3000.0);

            assertFalse(loan.isGuaranteedLoanAvailable());
        }
    }

    @Nested
    class getPersonalLoanInterestRate{
        @Test
        void shouldThenInterestRateBe4() {

            doReturn(true).when(customer).isIncomeEqualOrLowerThan(3000.0);

           assertEquals(4.0, loan.getPersonalLoanInterestRate());
        }

        // lança um teste de exception de error
        @Test
        void shouldThrowExceptionWhenIsNotAvailable() {

            doReturn(false).when(customer).isIncomeEqualOrLowerThan(3000.0);

            assertThrows(LoanNotAvailableException.class, () -> loan.getPersonalLoanInterestRate());
        }
    }

    @Nested
    class getGuarantedLoanInterestRate{
        @Test
        void shouldThenInterestRateBe3() {

            doReturn(true).when(customer).isIncomeEqualOrLowerThan(3000.0);

            assertEquals(3.0, loan.getGuaranteedLoanInterestRate());
        }

        // lança um teste de exception de error
        @Test
        void shouldThrowExceptionWhenIsNotAvailable() {

            doReturn(false).when(customer).isIncomeEqualOrLowerThan(3000.0);

            assertThrows(LoanNotAvailableException.class, () -> loan.getGuaranteedLoanInterestRate());
        }
    }

    @Nested
    class getConsignorLoanInterestRate{
        @Test
        void shouldThenInterestRateBe2() {

            doReturn(true).when(customer).isIncomeEqualOrGreaterThan(5000.0);

            assertEquals(2.0, loan.getConsigmentLoanInterestRate());
        }

        // lança um teste de exception de error
        @Test
        void shouldThrowExceptionWhenIsNotAvailable() {

            doReturn(false).when(customer).isIncomeEqualOrGreaterThan(5000.0);

            assertThrows(LoanNotAvailableException.class, () -> loan.getConsigmentLoanInterestRate());
        }
    }
}