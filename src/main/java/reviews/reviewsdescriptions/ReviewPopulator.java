package reviews.reviewsdescriptions;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner {

	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Override
	public void run(String...args) throws Exception{
		
		Category carpet=new Category("carpet");
				carpet=categoryRepo.save(carpet);
				Category wood=new Category("wood");
				wood=categoryRepo.save(wood);
				Category tile=new Category("tile");
				tile=categoryRepo.save(tile);
				
		Review review1=new Review("Joe's Floor Show", "Joes's wood foor replacement","I give Joe and his team 5 stars! They did a great job.  My new wood planks look amazing.","images/woodcat.jpg",wood);
		review1=reviewRepo.save(review1);
		Review review2=new Review("Matt's Carpet", "Matt's carpet installation","The carpet installed by this company is just ok.  It works for my business but it looks a lot cheaper than I thought it would. ","images/carpetfloor.jpg",carpet);
		review2=reviewRepo.save(review2);
		Review review3=new Review("Tile City", "Tile City tile installation","The installers were very profressional and the job looks good.","images/tilefloor.jpg",tile);
		review3=reviewRepo.save(review3);
}
}
