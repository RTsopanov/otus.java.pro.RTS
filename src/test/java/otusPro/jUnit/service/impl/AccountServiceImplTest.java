package otusPro.jUnit.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import otusPro.jUnit.dao.AccountDao;
import otusPro.jUnit.entity.Account;
import otusPro.jUnit.entity.Agreement;
import otusPro.jUnit.service.exception.AccountException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
    @Mock
    AccountDao accountDao;

    @InjectMocks
    AccountServiceImpl accountServiceImpl;

    @Test
    public void testTransfer() {
        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));

        Account destinationAccount = new Account();
        destinationAccount.setAmount(new BigDecimal(10));

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(eq(2L))).thenReturn(Optional.of(destinationAccount));

        accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));

        assertEquals(new BigDecimal(90), sourceAccount.getAmount());
        assertEquals(new BigDecimal(20), destinationAccount.getAmount());
    }

    @Test
    public void testSourceNotFound() {
        when(accountDao.findById(any())).thenReturn(Optional.empty());

        AccountException result = assertThrows(AccountException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));
            }
        });
        assertEquals("No source account", result.getLocalizedMessage());
    }

//    @Test
//    public void testDestinationNotFound() {
//        when(accountDao.findById(any())).thenReturn(Optional.empty());
//
//        AccountException result = assertThrows(AccountException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));
//            }
//        });
//        assertEquals("No source account", result.getLocalizedMessage());
//    }


    @Test
    public void testTransferWithVerify() {
        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));
        sourceAccount.setId(1L);

        Account destinationAccount = new Account();
        destinationAccount.setAmount(new BigDecimal(10));
        destinationAccount.setId(2L);

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(eq(2L))).thenReturn(Optional.of(destinationAccount));

        ArgumentMatcher<Account> sourceMatcher =
                argument -> argument.getId().equals(1L) && argument.getAmount().equals(new BigDecimal(90));

        ArgumentMatcher<Account> destinationMatcher =
                argument -> argument.getId().equals(2L) && argument.getAmount().equals(new BigDecimal(20));

        accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));

        verify(accountDao).save(argThat(sourceMatcher));
        verify(accountDao).save(argThat(destinationMatcher));
    }


    @Test
    public void testCharge() {
        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));
        sourceAccount.setId(1L);

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(sourceAccount));

        accountServiceImpl.charge(1L, new BigDecimal(10));

        assertEquals(new BigDecimal(90), sourceAccount.getAmount());
    }

@Test
    public void iterableToList() {
        assertEquals(0, accountServiceImpl.getAccounts().size());
    }



@Test
    public void addAccountsTest() {
        Account account = new Account();

        account.setAgreementId(1L);
        account.setNumber("1");
        account.setType(0);
        account.setAmount(new BigDecimal(10));

        assertEquals(0, accountServiceImpl.getAccounts().size());
        accountServiceImpl.addAccount(new Agreement(), "1", 0, new BigDecimal(10));
    }
}