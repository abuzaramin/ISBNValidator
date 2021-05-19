package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockManagementTests {

    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDatabaseService;
    StockManager stockManager;

    @BeforeEach
    public void setup () {
        System.out.println("Setup running");
        testWebService = mock(ExternalISBNDataService.class);
        testDatabaseService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
    }

    @Test
    public void testCanGetACorrectLocatorCode () {

        when(testWebService.lookup(anyString())).thenReturn(new Book ("0140177396","Of Mice And Men", "J Steinbeck"));
        when(testDatabaseService.lookup(anyString())).thenReturn(null);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals ("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {


        when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(testDatabaseService, times(1)).lookup("0140177396");
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfdataisNotpresentInDatabase() {

        when(testDatabaseService.lookup("0140177396")).thenReturn(null);
        when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(testDatabaseService, times(1)).lookup("0140177396");
        verify(testWebService, times(1)).lookup("0140177396");
    }
}
