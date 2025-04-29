package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.Wishlist;

import java.time.LocalDateTime;
import java.util.List;

public record WishlistDto(
        Long id,
        LocalDateTime dateCreated,
        UpdateUserDto user,
        List<UpdateBookDto> books
) {

    public static WishlistDto from(Wishlist wishlist) {
        return new WishlistDto(
                wishlist.getId(),
                wishlist.getDateCreated(),
                UpdateUserDto.from(wishlist.getUser()),
                UpdateBookDto.from(wishlist.getBooks())
        );
    }

}