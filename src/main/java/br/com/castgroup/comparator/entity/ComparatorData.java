package br.com.castgroup.comparator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Entity class, for holding the data used in comparison.
 * 
 * @author alsantos
 *
 */
@Entity
public class ComparatorData {
	/**
	 * The element id.
	 */
	@Id
	private long id;

	/**
	 * The left data.
	 */
	@Lob
	@Column(length = 100000)
	private String left;

	/**
	 * The right data.
	 */
	@Lob
	@Column(length = 100000)
	private String right;

	/**
	 * Empty constructor.
	 */
	public ComparatorData() {

	}

	/**
	 * Constructor for creating new instances with well-defined elements.
	 * 
	 * @param id    of the object. It must be informed. 
	 * @param left  side of data.
	 * @param right side of data.
	 */
	public ComparatorData(long id, String left, String right) {
		this.id = id;
		this.left = left;
		this.right = right;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
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
		ComparatorData other = (ComparatorData) obj;
		if (id != other.id)
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ComparatorData [id=" + id + ", left=" + left + ", right=" + right + "]";
	}

}
