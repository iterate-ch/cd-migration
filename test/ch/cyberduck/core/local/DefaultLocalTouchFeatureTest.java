package ch.cyberduck.core.local;

import ch.cyberduck.core.AbstractTestCase;
import ch.cyberduck.core.exception.AccessDeniedException;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @version $Id$
 */
public class DefaultLocalTouchFeatureTest extends AbstractTestCase {

    @Test
    public void testTouch() throws Exception {
        FinderLocal l = new FinderLocal(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString());
        final DefaultLocalTouchFeature f = new DefaultLocalTouchFeature();
        f.touch(l);
        assertTrue(l.exists());
        f.touch(l);
        assertTrue(l.exists());
        l.delete();
    }

    @Test(expected = AccessDeniedException.class)
    public void testFailure() throws Exception {
        FinderLocal l = new FinderLocal("/" + UUID.randomUUID().toString());
        final DefaultLocalTouchFeature f = new DefaultLocalTouchFeature();
        try {
            f.touch(l);
        }
        catch(AccessDeniedException e) {
            final String s = l.getName();
            assertEquals("Cannot create file /" + s + ". Please contact your web hosting service provider for assistance.", e.getDetail());
            assertEquals("Access denied", e.getMessage());
            throw e;
        }
    }
}
