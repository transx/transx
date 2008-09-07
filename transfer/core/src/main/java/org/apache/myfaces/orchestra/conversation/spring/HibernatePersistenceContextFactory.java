/* *Class created on [ Sep 1, 2008 | 12:51:42 PM ] */ 
package org.apache.myfaces.orchestra.conversation.spring;

import java.util.Stack;

import org.apache.myfaces.orchestra.conversation.spring.PersistenceContext;
import org.apache.myfaces.orchestra.conversation.spring.PersistenceContextFactory;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 *  This Class has some Licence Issue with Apache Licence <br/>
 *  <a href="http://mail-archives.apache.org/mod_mbox/myfaces-users/200801.mbox/%3C478132FC.9020002@ops.co.at%3E">resource</a> 
 */
public class HibernatePersistenceContextFactory implements PersistenceContextFactory{
    private SessionFactory sessionFactory;

    public PersistenceContext create()
    {
        final Session em = openSesssion();
        em.setFlushMode(FlushMode.COMMIT);

        return new PersistenceContext()
        {
            private final Stack bindings = new Stack();

            public void bind()
            {
            	SessionHolder current = (SessionHolder)TransactionSynchronizationManager.getResource(sessionFactory);
                synchronized(bindings)
                {
                    if (current != null)
                        TransactionSynchronizationManager.unbindResource(sessionFactory);

                    bindings.push(current);

                    TransactionSynchronizationManager.bindResource(sessionFactory,new SessionHolder(em));
                }
            }

            public void unbind()
            {
            	Object holder = null;
                synchronized(bindings)
                {
                    if (TransactionSynchronizationManager.hasResource(sessionFactory))
                        TransactionSynchronizationManager.unbindResource(sessionFactory);

                    if (bindings.size() > 0)
                        holder = bindings.pop();
                    
                    if (holder != null)
                        TransactionSynchronizationManager.bindResource(sessionFactory,holder);
                }
            }

            public void close()
            {
                em.close();
            }
        };
    }

    public SessionFactory getEntityManagerFactory()
    {
        return sessionFactory;
    }

    public void setEntityManagerFactory(SessionFactory entityManagerFactory)
    {
        this.sessionFactory = entityManagerFactory;
    }

    protected Session openSesssion()
    {
        return sessionFactory.openSession();
    }
}