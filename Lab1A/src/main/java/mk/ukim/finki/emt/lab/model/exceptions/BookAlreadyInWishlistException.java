package mk.ukim.finki.emt.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class BookAlreadyInWishlistException extends RuntimeException{

    public BookAlreadyInWishlistException(Long id, String username) {
        super(String.format("Book with id: %d already exists in wishlist for user with username %s", id, username));
    }
}
