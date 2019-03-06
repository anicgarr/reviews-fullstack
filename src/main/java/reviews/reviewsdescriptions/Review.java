package reviews.reviewsdescriptions;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity 
public class Review {
	
	
	@Id
	@GeneratedValue
	private long id;
	
	
	private String name;
	private String description;
	private String imageUrl;
	
	@Lob
	private String reviewText;
	
	@ManyToMany
	private Collection<Category> categories;
	
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description; 
	}
	
	public String getReviewText() {
		return reviewText;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public Review() {  //default no arg constructor that jpa requires 
		
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Review(String name, String description,String reviewText,String imageUrl,Category...categories) {
		this.name=name;
		this.description=description;
		this.reviewText=reviewText;
		this.imageUrl=imageUrl;
		this.categories=new HashSet<>(Arrays.asList(categories));
	}

	public Collection<Category> getCategories() {
		return categories;
	}

	
}
