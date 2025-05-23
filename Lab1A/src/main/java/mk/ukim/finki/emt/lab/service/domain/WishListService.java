package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.Wishlist;

import java.util.List;
import java.util.Optional;

public interface WishListService {
    List<Book> listAllBooksInWishlist(Long wishlistId);

    Optional<Wishlist> getActiveWishlist(String username);

    Optional<Wishlist> addBookToWishlist(String username, Long bookId);
}

