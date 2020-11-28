package Project1;

public class TRMSAuthControllerTest {
	@RunWith(MockitoJUnitRunner.class)
	public class UserControlTest {
	    @Mock
	    private UserService mockService;
	    @Mock
	    private Context mockCtx;
	    private UserControl controlToTest;
	    private User user;
	    @BeforeClass
	    public static void setUpBeforeClass() throws Exception {
	    }
	    @AfterClass
	    public static void tearDownAfterClass() throws Exception {
	    }
	    @Before
	    public void setUp() throws Exception {
	        controlToTest = new UserControl(mockService);
	        user = new User(2010, "username", "hashedpassword123467890", 0, AuthPriv.EMPLOYEE);
	        when(mockCtx.formParam("userId")).thenReturn(Integer.toString(user.getUserId()));
	        when(mockCtx.formParam("username")).thenReturn(user.getUsername());
	        when(mockCtx.formParam("password")).thenReturn(user.getPassword());
	        when(mockCtx.formParam("employeeId")).thenReturn(Integer.toString(user.getEmployeeId()));
	        when(mockCtx.formParam("privilege")).thenReturn(user.getPrivilege().toString());
	    }
	    @After
	    public void tearDown() throws Exception {
	    }
	    @Test
	    public void createUserTest() {
	        try {
	            controlToTest.createUser(mockCtx);
	            verify(mockCtx).formParam("userId");
	            verify(mockCtx).formParam("username");
	            verify(mockCtx).formParam("password");
	            verify(mockCtx).formParam("employeeId");
	            verify(mockCtx).formParam("privilege");
	            verify(mockService).createUser(user.getUsername(), user.getPassword(), user.getEmployeeId(), user.getPrivilege());
	            //TODO verify ctx being given proper inputs.
	            
	            verify(mockCtx).status(200);
	        } catch (Exception e) {
	            fail("Exception thrown during create test: " + e);
	        }

}
