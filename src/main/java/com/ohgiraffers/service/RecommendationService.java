package com.ohgiraffers.service;

import com.ohgiraffers.dto.BookDTO;
import java.util.Calendar;
import java.util.List;

public class RecommendationService {

    private final BookService bookService;

    public RecommendationService() {
        this.bookService = new BookService();
    }

    public BookDTO getHourlyRecommendedBook() {
        List<BookDTO> allBooks = bookService.selectAllBooks();

        if (allBooks == null || allBooks.isEmpty()) {
            return null;
        }

        // Use hour and date to pick a book
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int day = cal.get(Calendar.DAY_OF_YEAR);

        // Simple algorithm: shift index by hour and day
        // Ensures it changes every hour
        long index = (long) hour + day;
        int bookIndex = (int) (index % allBooks.size());

        return allBooks.get(bookIndex);
    }
}
