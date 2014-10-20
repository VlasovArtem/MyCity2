package org.hillel.it.mycity.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.hillel.it.mycity.model.entity.BaseEntity;

public class GlobalInterceptor extends EmptyInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		System.out.println("onFlushDirty");
		return super.onFlushDirty(entity, id, currentState, previousState,
				propertyNames, types);
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		System.out.println("onSave");
		if(entity instanceof BaseEntity) {
			for(int i = 0; i < propertyNames.length; i++) {
				String property = propertyNames[i];
				if(property.equals("created")) {
					state[i] = new Date();
					return true;
				}
			}
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public void postFlush(Iterator entities) {
		System.out.println("postFlush");
		super.postFlush(entities);
	}

	@Override
	public void preFlush(Iterator entities) {
		System.out.println("preFlush");
		super.preFlush(entities);
	}

	@Override
	public void afterTransactionBegin(Transaction tx) {
		System.out.println("afterBegin");
		super.afterTransactionBegin(tx);
	}

	@Override
	public void afterTransactionCompletion(Transaction tx) {
		System.out.println("afterCompletion");
		super.afterTransactionCompletion(tx);
	}

	@Override
	public void beforeTransactionCompletion(Transaction tx) {
		System.out.println("beforeCompletion");
		super.beforeTransactionCompletion(tx);
	}
	
}
