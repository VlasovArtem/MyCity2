package org.hillel.it.mycity.persistence.repository.inmemory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.model.entity.PersonGroup;
import org.hillel.it.mycity.model.entity.Moderator;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.repository.UserRepository;

@Deprecated
public class FileUserRepository extends InMemoryUserRepository implements UserRepository {

	private File file;

	public FileUserRepository(){
	}

	protected <T>void sereializeUserData(T t, PersonGroup group) {
		switch (group) {
		case User:
			file = new File("userData.bin");
			break;
		case Moderator:
			file = new File("moderatorData.bin");
			break;
		case Administrator:
			file = new File("administratorData.bin");
			break;
		default:
			System.out.println("This is no such User group");
			return;
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SerializationUtils.serialize((Serializable) t, fos);
	}

	protected void deserializeUserData() throws IOException, ClassNotFoundException, FileNotFoundException {
		FileInputStream fis = null;
		file = new File("administratorData.bin");
		if(file.exists()) {
			fis = new FileInputStream(file);
			administrators = (List<Administrator>) SerializationUtils.deserialize(fis);
			for(Administrator administrator : administrators) {
				if(administrator.getId() > maxId) {
					maxId = administrator.getId() + 1;
				}
				userMap.put(administrator.getId(), PersonGroup.Administrator);
				emailSet.add(administrator.getEmail());
			}
		}
		file = new File("moderatorData.bin");
		if(file.exists()) {
			fis = new FileInputStream(file);
			moderators = (List<Moderator>) SerializationUtils.deserialize(fis);
			for(Moderator moderator : moderators) {
				if(moderator.getId() > maxId) {
					maxId = moderator.getId() + 1;
				}
				userMap.put(moderator.getId(), PersonGroup.Moderator);
				emailSet.add(moderator.getEmail());
			}
		}
		file = new File("userData.bin");
		if(file.exists()) {
			fis = new FileInputStream(file);
			users = (List<User>) SerializationUtils.deserialize(fis);
			for(User user: users) {
				if(user.getId() > maxId) {
					maxId = user.getId() + 1;
				}
				userMap.put(user.getId(), PersonGroup.User);
				emailSet.add(user.getEmail());
			}
		}
	}
	
	@Override
	public <T>void flush(T t, PersonGroup group) {
		sereializeUserData(t, group);
	}
	
	@Override
	public void deserializeData() {
		try {
			deserializeUserData();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
