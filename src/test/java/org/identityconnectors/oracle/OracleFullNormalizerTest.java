/**
 *
 */
package org.identityconnectors.oracle;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeBuilder;
import org.identityconnectors.framework.common.objects.AttributeUtil;
import org.identityconnectors.framework.common.objects.Name;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.Uid;
import org.testng.annotations.Test;

/**
 * Tests for OracleAttributeNormalizer
 *
 * @author kitko
 *
 */
public class OracleFullNormalizerTest {

    @Test
    public void testNormalizeAttribute() {
        OracleFullNormalizer normalizer =
                new OracleFullNormalizer(OracleConfigurationTest.createSystemConfiguration()
                        .getCSSetup());
        ObjectClass objectClass = ObjectClass.ACCOUNT;

        assertNull(normalizer.normalizeAttribute(objectClass, null));

        Attribute attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build("dummy",
                        "dummyValue"));
        assertNotNull(attr);
        assertEquals("dummyValue", AttributeUtil.getSingleValue(attr));

        // User is by default case sensitive/insensitive, depends on
        // OracleUserAttributeCS
        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(Name.NAME,
                        "myName"));
        assertNotNull(attr);
        assertEquals(OracleUserAttribute.USER.getFormatting().isToUpper() ? "myName".toUpperCase()
                : "myName", AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(Name.NAME
                        .toLowerCase(), "myName"));
        assertNotNull(attr);
        assertEquals(OracleUserAttribute.USER.getFormatting().isToUpper() ? "myName".toUpperCase()
                : "myName", AttributeUtil.getSingleValue(attr));

        // We normalize also UID
        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder
                        .build(Uid.NAME, "myUid"));
        assertNotNull(attr);
        assertEquals("MYUID", AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(Uid.NAME
                        .toLowerCase(), "myUid"));
        assertNotNull(attr);
        assertEquals("MYUID", AttributeUtil.getSingleValue(attr));

        // By default we do not uppercase globalname
        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_GLOBAL_ATTR_NAME, "myGlobalName"));
        assertNotNull(attr);
        assertEquals("myGlobalName", AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_PROFILE_ATTR_NAME, "myProfile"));
        assertNotNull(attr);
        assertEquals("myProfile".toUpperCase(), AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_PROFILE_ATTR_NAME.toLowerCase(), "myProfile"));
        assertNotNull(attr);
        assertEquals("myProfile".toUpperCase(), AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_DEF_TS_ATTR_NAME, "myDefTs"));
        assertNotNull(attr);
        assertEquals("myDefTs".toUpperCase(), AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_DEF_TS_ATTR_NAME.toLowerCase(), "myDefTs"));
        assertNotNull(attr);
        assertEquals("myDefTs".toUpperCase(), AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_TEMP_TS_ATTR_NAME, "myTempTs"));
        assertNotNull(attr);
        assertEquals("myTempTs".toUpperCase(), AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_TEMP_TS_ATTR_NAME.toLowerCase(), "myTempTs"));
        assertNotNull(attr);
        assertEquals("myTempTs".toUpperCase(), AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_PRIVS_ATTR_NAME, "myPriv"));
        assertNotNull(attr);
        assertEquals("myPriv".toUpperCase(), AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_PRIVS_ATTR_NAME.toLowerCase(), "myPriv"));
        assertNotNull(attr);
        assertEquals("myPriv".toUpperCase(), AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_ROLES_ATTR_NAME, "myRole"));
        assertNotNull(attr);
        assertEquals("myRole".toUpperCase(), AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_ROLES_ATTR_NAME.toLowerCase(), "myRole"));
        assertNotNull(attr);
        assertEquals("myRole".toUpperCase(), AttributeUtil.getSingleValue(attr));

        attr =
                normalizer.normalizeAttribute(objectClass, AttributeBuilder.build(
                        OracleConstants.ORACLE_ROLES_ATTR_NAME.toUpperCase(), "myRole"));
        assertNotNull(attr);
        assertEquals("myRole".toUpperCase(), AttributeUtil.getSingleValue(attr));

    }
}
