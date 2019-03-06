package reviews.reviewsdescriptions;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private long id;
	
	
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	private Collection<Review> reviews;
	
	

	public Category(String name) {
		this.name=name;
	} 

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	//default no arg constructor required by jpa
	public Category() {
		
	}
	
	
	public Collection<Review> getReviews() {
		return reviews;
	}


	public void setReviews(Collection<Review> reviews) {
		this.reviews = reviews;
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}


	

}
