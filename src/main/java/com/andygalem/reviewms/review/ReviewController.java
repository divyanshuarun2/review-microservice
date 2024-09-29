package com.andygalem.reviewms.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllreview(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllreviews(companyId), HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review){
        boolean isCreated= reviewService.createReview(companyId,review);
        if(isCreated){
            return ResponseEntity.ok("Review for company ID:"+companyId+" is created");
        }
        return new ResponseEntity<>("Company Id:"+companyId+" not found, fail to create Review!!",HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){
        Review ispresent = reviewService.getReview(reviewId);
        if(ispresent!=null)
    return new ResponseEntity<>(ispresent,HttpStatus.OK);
        return new ResponseEntity<Review>(ispresent,HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review){

       boolean isUpdated= reviewService.updateReview(reviewId,review);

       if(isUpdated){
         return new ResponseEntity<>("review updated successfully!!",HttpStatus.OK);
       }
       return new ResponseEntity<>("Either company or review not found!!",HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isDeleted= reviewService.deleteReview(reviewId);
        if(isDeleted){
            return new ResponseEntity<>("Review Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Either company or review is incorrect!",HttpStatus.NOT_FOUND);

    }


}
