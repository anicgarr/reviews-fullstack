package reviews.reviewsdescriptions;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.containsInAnyOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();

		entityManager.flush(); // forces jpa to hit the database when when try to find it
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category=result.get();
		assertThat(category.getName(), is("category"));
	}

	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(categoryId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadReview() {
		Review review = reviewRepo.save(new Review("review name", "description","fff", null, null));
		review = reviewRepo.save(review);
		long reviewId = review.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review=result.get();
		assertThat(review.getName(), is("review name"));

	}

	@Test
	public void shouldEstablishReviewToCategoriesRelationships() {
		// categories is not the owner so we create these first
		Category diapers = categoryRepo.save(new Category("Diapers"));
		Category bath = categoryRepo.save(new Category("Bath"));

		Review review = new Review("Best ever", "description","fff","dd", bath, diapers);
		review = reviewRepo.save(review);
		long reviewId = review.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();

		assertThat(review.getCategories(), containsInAnyOrder(bath, diapers));
	}

	@Test
	public void shouldBeAbleToFindReviewsForCategory() {
		Category diapers = categoryRepo.save(new Category("diapers"));

		Review bestEver = reviewRepo.save(new Review("Best Ever", "Description","fff","ddd",diapers));
		Review worstEver = reviewRepo.save(new Review("Worst Ever", "Description","fff","ddd", diapers));

		entityManager.flush();
		entityManager.clear();

		Collection<Review> reviewsForCategory = reviewRepo.findByCategoriesContains(diapers);

		assertThat(reviewsForCategory, containsInAnyOrder(bestEver, worstEver));
	}

	@Test
	public void shouldFindReviewsForCategoryId() {
		Category diapers = categoryRepo.save(new Category("Diapers"));
		long categoryId = diapers.getId();

		Review bestEver = reviewRepo.save(new Review("Best Ever", "Description","fff","fff", diapers));
		Review worstEver = reviewRepo.save(new Review("Worst Ever", "Description","fff","dd", diapers));

		entityManager.flush();
		entityManager.clear();

		Collection<Review> reviewsForCategory = reviewRepo.findByCategoriesId(categoryId);

		assertThat(reviewsForCategory, containsInAnyOrder(bestEver, worstEver));

	}
}
