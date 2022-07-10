public class UserService {


    public UserService() {

    }

    public static User getDefaultUser() {
        User newUserData = new User();
        newUserData.setId(1);
        newUserData.setName("Ole Chentsov");
        newUserData.setUsername("Olch");
        newUserData.setEmail("olch@akkerman.dk");

        Address address = new Address();
        address.setCity("Mukhosransk");
        address.setStreet("Gepaschtrasse");
        address.setSuite("111");
        address.setZipcode("210021");

        Geo geo = new Geo();
        geo.setLat(39.429859);
        geo.setLng(-76.355569);
        address.setGeo(geo);

        newUserData.setAddress(address);
        newUserData.setPhone("123-456-7890");
        newUserData.setWebsite("biir.dk");

        Company company = new Company();
        company.setName("" + "Akkerman engineering ");
        company.setCatchPhrase("Keep calm and be an engineer");
        company.setBs("lorem ipsum");

        newUserData.setCompany(company);
        return newUserData;
    }
}