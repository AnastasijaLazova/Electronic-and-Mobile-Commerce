package mk.ukim.finki.emt.lab.service.application.impl;


import mk.ukim.finki.emt.lab.dto.UpdateBookDto;
import mk.ukim.finki.emt.lab.dto.WishlistDto;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.repository.UserRepository;
import mk.ukim.finki.emt.lab.service.application.WishlistApplicationService;
import mk.ukim.finki.emt.lab.service.domain.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistApplicationServiceImpl implements WishlistApplicationService {

    private final WishListService wishlistService;

    public WishlistApplicationServiceImpl(WishListService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public List<UpdateBookDto> listAllBooksInWishlist(Long wishlistId) {
        return UpdateBookDto.from(wishlistService.listAllBooksInWishlist(wishlistId));
    }

    @Override
    public Optional<WishlistDto> getActiveWishlist(String username) {
        return wishlistService.getActiveWishlist(username).map(WishlistDto::from);
    }

    @Override
    public Optional<WishlistDto> addBookToWishlist(String username, Long bookId) {
        return wishlistService.addBookToWishlist(username, bookId).map(WishlistDto::from);
    }

}
