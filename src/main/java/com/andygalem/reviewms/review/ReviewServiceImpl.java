package com.andygalem.reviewms.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    public List<Review> getAllreviews(Long companyId) {
        List<Review> reviews = reviewRepo.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        if(companyId!=null && review!=null){
        review.setCompanyId(companyId);
            reviewRepo.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long reviewId) {

       return reviewRepo.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
      Review review=reviewRepo.findById(reviewId).orElse(null);
        if(review!=null){
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setTitle(updatedReview.getTitle());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepo.save(review);
            return true;

        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review=reviewRepo.findById(reviewId).orElse(null);

            if(review!=null){
                reviewRepo.deleteById(reviewId);
                return true;
            }
        return false;

    }
}

