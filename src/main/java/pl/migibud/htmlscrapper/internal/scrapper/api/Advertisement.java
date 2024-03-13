package pl.migibud.htmlscrapper.internal.scrapper.api;

import java.time.LocalDate;

public record Advertisement(String price, String title, String address, String description, LocalDate createdAt) {
}
