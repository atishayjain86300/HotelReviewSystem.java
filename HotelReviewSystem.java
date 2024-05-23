import java.util.ArrayList;
import java.util.List;

class Hotel {
    private String name;
    private List<Review> reviews;

    public Hotel(String name) {
        this.name = name;
        this.reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getName() {
        return name;
    }
}

class Review {
    private String text;
    private int rating;

    public Review(String text, int rating) {
        this.text = text;
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }
}

public class HotelReviewSystem {
    private List<Hotel> hotels;

    public HotelReviewSystem() {
        this.hotels = new ArrayList<>();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void addReview(String hotelName, Review review) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(hotelName)) {
                hotel.addReview(review);
                return;
            }
        }
    }

    public List<Review> getReviews(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(hotelName)) {
                return hotel.getReviews();
            }
        }
        return new ArrayList<>();
    }

    public void sortReviewsByRating(String hotelName) {
        List<Review> reviews = getReviews(hotelName);
        reviews.sort((r1, r2) -> Integer.compare(r2.getRating(), r1.getRating()));
    }

    public void filterReviewsByRating(String hotelName, int minRating) {
        List<Review> reviews = getReviews(hotelName);
        reviews.removeIf(r -> r.getRating() < minRating);
    }

    public static void main(String[] args) {
        HotelReviewSystem system = new HotelReviewSystem();

        Hotel hotel1 = new Hotel("Hotel 1");
        hotel1.addReview(new Review("Good hotel", 4));
        hotel1.addReview(new Review("Bad hotel", 2));
        hotel1.addReview(new Review("Okay hotel", 3));

        Hotel hotel2 = new Hotel("Hotel 2");
        hotel2.addReview(new Review("Excellent hotel", 5));
        hotel2.addReview(new Review("Average hotel", 3));

        system.addHotel(hotel1);
        system.addHotel(hotel2);

        system.sortReviewsByRating("Hotel 1");
        system.filterReviewsByRating("Hotel 2", 4);

        for (Hotel hotel : system.hotels) {
            System.out.println("Hotel: " + hotel.getName());
            for (Review review : hotel.getReviews()) {
                System.out.println("Review: " + review.getText() + ", Rating: " + review.getRating());
            }
        }
    }
}
