package reviews.reviewsdescriptions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {
	
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@Mock Category category;
	
	@Mock Category anotherCategory;
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;
	@Test
	public void shouldRouteToSingleReviewView() throws Exception{
		long arbitraryReviewId=1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
	}
	
	@Test
	public void shouldBeOkForSingleReview() throws Exception{
		long arbitraryReviewId=1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}
	
	@Test 
	public void shouldNotBeOkForSingleReview() throws Exception{
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());

	}
	
	@Test 
	public void shouldPutSingleReviewIntoModel() throws Exception{
		when(reviewRepo.findById(1L)).thenReturn(Optional.of(review));
		
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("reviews",is(review)));
	}
@Test 
public void shouldRouteToAllReviewsView() throws Exception{
	mvc.perform(get("/reviews")).andExpect(view().name(is("reviews")));

}

@Test
public void shouldBeOkforAllReviews() throws Exception{
	mvc.perform(get("/reviews")).andExpect(status().isOk());
	
}

@Test
public void shouldPutAllReviewsIntoModel() throws Exception {
	Collection<Review> allReviews=Arrays.asList(review,anotherReview);
	when(reviewRepo.findAll()).thenReturn(allReviews);
	
	mvc.perform(get("/reviews")).andExpect(model().attribute("reviews", is(allReviews)));
}

@Test
public void shouldRouteToSingleCategoryView() throws Exception{
	long arbitraryCategoryId=1;
	when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
	mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
}

@Test
public void shouldBeOkForSingleCategory() throws Exception{
	long arbitraryCategoryId=1;
	when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
	mvc.perform(get("/category?id=1")).andExpect(status().isOk());
}

@Test 
public void shouldNotBeOkForSingleCategory() throws Exception{
	mvc.perform(get("/category?id=1")).andExpect(status().isNotFound());

}

@Test 
public void shouldPutSingleCategoryIntoModel() throws Exception{
	when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
	
	mvc.perform(get("/category?id=1")).andExpect(model().attribute("categories",is(category)));
}
@Test 
public void shouldRouteToAllCategoriesView() throws Exception{
mvc.perform(get("/categories")).andExpect(view().name(is("categories")));

}

@Test
public void shouldBeOkforAllCategories() throws Exception{
mvc.perform(get("/categories")).andExpect(status().isOk());

}

@Test
public void shouldPutAllCategoriesIntoModel() throws Exception {
Collection<Category> allCategories=Arrays.asList(category,anotherCategory);
when(categoryRepo.findAll()).thenReturn(allCategories);

mvc.perform(get("/categories")).andExpect(model().attribute("categories", is(allCategories)));
}


}
