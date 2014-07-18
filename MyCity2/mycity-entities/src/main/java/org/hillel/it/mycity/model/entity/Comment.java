package org.hillel.it.mycity.model.entity;

	/*
	 * @timur Думаю объект класса Comment должен представлять 
	 * один комментарий. Один комментарий - это комментарий 
	 * одного пользователя (user_id) к одному заведению (establishment_id).
	 * 
	 * Потом уже будем делать выборки этих комментариев как угодно: 
	 * 1) Список всех комментриев к заведению. Например, это будет поле класса Establishment.
	 * 2) Список всех комментариев пользователя. Например, это будет поле класса RegisteredUser. 
	 * 
	 */
public class Comment extends BaseEntity{
	
	private int commentAssessment;
	private String comment;
	private boolean needToModerate;
	private Establishment establishment;
	
	public Comment() {
		commentAssessment = 0;
		needToModerate = false;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
	//наверно нужно добавить проверка, на количество вызовов метода одним пользователем. 
	public void setCommentPositiveAssessment() {
		++commentAssessment;
	}
	
	public void setCommentNegativeAssessment() {
		--commentAssessment;
	}
	
	public int getCommentAssessment() {
		return commentAssessment;
	}
	
	public void setCommentToModerate(Person user) {
		checkUserForComment(user);
		needToModerate = true;
	}
	
	public boolean checkCommentForModeration() {
		return needToModerate;
	}
	
	/**
	 * This method add Establishment object to the Comment object. Check is this comment establishment 
	 * is not empty.
	 * @param establishment
	 */
	public void setEstablishment(Establishment establishment) {
		checkDataIsNotNull(this.establishment, "You can not add additional Establishment to this Comment");
		this.establishment = establishment;
	}
	
	/**
	 * Method that return true if <code>Establishment</code> of this Comment object is equals
	 * to Establishment object that get by argument.
	 * @param establishment
	 */
	public boolean checkEstablishment(Establishment establishment) {
		return this.establishment.equals(establishment);
	}
	
	public void checkUserForComment(Person user) {
		if(!user.inGroup(Group.Moderator) && !user.inGroup(Group.Administrator)) {
			throw new RuntimeException("This user can`t mark comment to moderate");
		}
	}
}
