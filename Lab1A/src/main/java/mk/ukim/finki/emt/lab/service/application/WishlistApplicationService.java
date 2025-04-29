package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.UpdateBookDto;
import mk.ukim.finki.emt.lab.dto.WishlistDto;
import java.util.List;
import java.util.Optional;

public interface WishlistApplicationService {
    List<UpdateBookDto> listAllBooksInWishlist(Long wishlistId);

    Optional<WishlistDto> getActiveWishlist(String username);

    Optional<WishlistDto> addBookToWishlist(String username, Long bookId);
}

