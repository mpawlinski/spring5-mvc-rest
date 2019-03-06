package pawlinski.spring5mvcrest.api.v1.mapper;

import org.junit.Before;
import org.junit.Test;
import pawlinski.spring5mvcrest.api.v1.model.VendorDTO;
import pawlinski.spring5mvcrest.domain.Vendor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VendorMapperTest {

    public static final String VENDOR_NAME = "Exotica Fruitica";
    public static final long ID = 1L;

    Vendor vendor;

    @Before
    public void setUp() throws Exception {
        vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(VENDOR_NAME);
    }

    @Test
    public void testVendorMapper() {
        VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDto(vendor);

        assertNotNull(vendorDTO);
        assertEquals(Long.valueOf(ID), vendorDTO.getId());
        assertEquals(VENDOR_NAME, vendorDTO.getName());
    }

}