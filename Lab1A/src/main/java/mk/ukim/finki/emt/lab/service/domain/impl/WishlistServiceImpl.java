package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.domain.Wishlist;
import mk.ukim.finki.emt.lab.model.exceptions.BookAlreadyInWishlistException;
import mk.ukim.finki.emt.lab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.model.exceptions.WishlistNotFoundException;
import mk.ukim.finki.emt.lab.repository.WishListRepository;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import mk.ukim.finki.emt.lab.service.domain.WishListService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service
public class WishlistServiceImpl implements WishListService {

    private final WishListRepository wishlistRepository;
    private final UserService userService;
    private final BookService bookService;

    public WishlistServiceImpl(WishListRepository wishlistRepository, UserService userService, BookService bookService) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public List<Book> listAllBooksInWishlist(Long wishlistId) {
        if (wishlistRepository.findById(wishlistId).isEmpty())
            throw new WishlistNotFoundException(wishlistId);
        return wishlistRepository.findById(wishlistId).get().getBooks();
    }

    @Override
    public Optional<Wishlist> getActiveWishlist(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(wishlistRepository.findByUser(user).
                orElseGet(() -> wishlistRepository.save(new Wishlist(user))));

    }

    @Override
    public Optional<Wishlist> addBookToWishlist(String username, Long bookId) {
        if (getActiveWishlist(username).isPresent()) {
            Wishlist wishlist = getActiveWishlist(username).get();

            Book book = bookService.findById(bookId)
                    .orElseThrow(() -> new BookNotFoundException(bookId));
            if (!wishlist.getBooks().stream().filter(i -> i.getId().equals(bookId)).toList().isEmpty())
                throw new BookAlreadyInWishlistException(bookId, username);
            wishlist.getBooks().add(book);
            return Optional.of(wishlistRepository.save(wishlist));
        }
        return Optional.empty();

    }
}
