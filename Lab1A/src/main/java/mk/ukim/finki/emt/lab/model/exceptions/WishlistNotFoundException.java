package mk.ukim.finki.emt.lab.model.exceptions;

public class WishlistNotFoundException extends RuntimeException {
    public WishlistNotFoundException(Long id) {
        super(String.format("Wishlist with id: %d was not found", id));
    }
}
