package de.uni_mannheim.bwl.schader.odm.garedo.client;

import java.sql.Date;
import java.util.List;

import de.uni_mannheim.bwl.schader.odm.garedo.client.model.Profile;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.User;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.DTO.UserDTO;
import de.uni_mannheim.bwl.schader.odm.garedo.client.services.ProjectService;
import de.uni_mannheim.bwl.schader.odm.garedo.client.services.ProjectServiceAsync;
import de.uni_mannheim.bwl.schader.odm.garedo.client.services.UserService;
import de.uni_mannheim.bwl.schader.odm.garedo.client.services.UserServiceAsync;
import de.uni_mannheim.bwl.schader.odm.garedo.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GaredoPCR implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
//	private static final String SERVER_ERROR = "An error occurred while "
//			+ "attempting to contact the server. Please check your network "
//			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final UserServiceAsync userService = GWT.create(UserService.class);
	private final ProjectServiceAsync projectService = GWT.create(ProjectService.class);

	private UserDTO currentUserDTO;
	private Profile currentProfile;
	
	final Label errorLabel = new Label();
	private final HorizontalPanel dashboardPanel = new HorizontalPanel();
	private final VerticalPanel profilePanel = new VerticalPanel();
    private final VerticalPanel projectPanel = new VerticalPanel();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		loadLogin();
	}
	
	private void loadLogin() {
		
		final Button loginButton = new Button("Log In");
		final TextBox loginField = new TextBox();
		loginField.setText("");
		
		final Button signupButton = new Button("Sign Up");
		final TextBox signupField = new TextBox();
		signupField.setText("");
		
		// We can add style names to widgets
		loginButton.addStyleName("sendButton");
		signupButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("loginFieldContainer").add(loginField);
		RootPanel.get("loginButtonContainer").add(loginButton);
		RootPanel.get("signupFieldContainer").add(signupField);
		RootPanel.get("signupButtonContainer").add(signupButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		loginField.setFocus(true);
		loginField.selectAll();

		// Create the popup dialog box
//		final DialogBox dialogBox = new DialogBox();
//		dialogBox.setText("Remote Procedure Call");
//		dialogBox.setAnimationEnabled(true);
//		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
//		closeButton.getElement().setId("closeButton");
//		final Label textToServerLabel = new Label();
//		final HTML serverResponseLabel = new HTML();
//		VerticalPanel dialogVPanel = new VerticalPanel();
//		dialogVPanel.addStyleName("dialogVPanel");
//		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
//		dialogVPanel.add(textToServerLabel);
//		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
//		dialogVPanel.add(serverResponseLabel);
//		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//		dialogVPanel.add(closeButton);
//		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
//		closeButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				dialogBox.hide();
//				loginButton.setEnabled(true);
//				loginButton.setFocus(true);
//			}
//		});

		// Create a handler for the loginButton and loginField
		class LoginHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				login();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					login();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void login() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = loginField.getText();
//				if (!FieldVerifier.isValidName(textToServer)) {
//					errorLabel.setText("Please enter at least four characters");
//					return;
//				}

				// Then, we send the input to the server.
//				loginButton.setEnabled(false);
//				signupButton.setEnabled(false);
//				textToServerLabel.setText(textToServer);
//				serverResponseLabel.setText("");
				
				userService.loadUser(textToServer,
						new AsyncCallback<UserDTO>() {
					
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
//								dialogBox
//										.setText("Remote Procedure Call - Failure");
//								serverResponseLabel
//										.addStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(SERVER_ERROR + "\n" + caught.getMessage());
//								dialogBox.center();
//								closeButton.setFocus(true);
								errorLabel.setText(caught.getMessage());
							}

							public void onSuccess(UserDTO userDTO) {
//								dialogBox.setText("Remote Procedure Call");
//								serverResponseLabel
//										.removeStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(user.getName());
//								dialogBox.center();
//								closeButton.setFocus(true);
								errorLabel.setText("SUCCESS! Logged on as " + userDTO.getName());
								currentUserDTO = userDTO;
								loadDashboard();
							}
						});
			}
		}

		// Create a handler for the signupButton and signupField
		class SignupHandler implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				signup();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					signup();
				}
			}

			private void signup() {
				errorLabel.setText("");
				String textToServer = signupField.getText();

//				loginButton.setEnabled(false);
//				signupButton.setEnabled(false);
				
				userService.createUser(textToServer,
						new AsyncCallback<Integer>() {
					
							public void onFailure(Throwable caught) {
								errorLabel.setText(caught.getMessage());
							}

							public void onSuccess(Integer id) {
								errorLabel.setText("SUCCESS! Created user with id: " + id);
							}
						});
			}
		}
		
		// Add a handler to send the name to the server
		LoginHandler loginHandler = new LoginHandler();
		loginButton.addClickHandler(loginHandler);
		loginField.addKeyUpHandler(loginHandler);
		
		SignupHandler signupHandler = new SignupHandler();
		signupButton.addClickHandler(signupHandler);
		signupField.addKeyUpHandler(signupHandler);
	}
	
	
	private void loadDashboard() {
		
	    RootPanel.get().clear();
	    
	    // main page layout
	    RootPanel.get().add(dashboardPanel);
	    dashboardPanel.add(profilePanel);
	    dashboardPanel.add(projectPanel);
	    
	    userService.loadProfile(currentUserDTO.getProfileId(),
	    		new AsyncCallback<Profile>() {
			
			public void onFailure(Throwable caught) {
				errorLabel.setText(caught.getMessage());
			}

			public void onSuccess(Profile profile) {
				// not null
				currentProfile = profile;
				addProfileInformation();
			}
		});
	    
	    addProjectInformation();
	    
//	    userService.loadProjects(currentUserDTO.getId(),
//	    		new AsyncCallback<Profile>() {
//			
//			public void onFailure(Throwable caught) {
//				//errorLabel.setText(caught.getMessage());
//			}
//
//			public void onSuccess(Profile profile) {
//				// not null
//				addProfileInformation(profile);
//			}
//		});
	    
	}
	
	private void addProfileInformation() {
	    
	    final Label profileLabel = new Label("Profile:");
	    profilePanel.add(profileLabel);
	    
	    final Button saveProfileButton = new Button("Save");
	    profilePanel.add(saveProfileButton);
		
	    final HorizontalPanel birthDatePanel = new HorizontalPanel();
	    profilePanel.add(birthDatePanel);
	    final Label birthDateLabel = new Label("Birth Date");
		final TextBox birthDateField = new TextBox();
		String birthDateString = "";
		if(currentProfile.getBirthDate() != null) {
			birthDateString = currentProfile.getBirthDate().toString();
		}
		birthDateField.setText(birthDateString);
	    birthDatePanel.add(birthDateLabel);
	    birthDatePanel.add(birthDateField);
	    
	    final Button showAddQualificationButton = new Button("Add Qualification");
	    profilePanel.add(showAddQualificationButton);
	    
	    final Grid qualificationGrid = new Grid(currentProfile.getQualifications().size(),2);
	    List<String> qualifications = currentProfile.getQualifications();
	    for(int i=0; i<qualifications.size(); i++) {
	    	qualificationGrid.setText(i,0,"Qualification: ");
	    	qualificationGrid.setText(i,1,qualifications.get(i));
	    }
	    profilePanel.add(qualificationGrid);
	    
	 
 		class SaveProfileHandler implements ClickHandler {
 			public void onClick(ClickEvent event) {
 				updateProfile();
 			}

 			private void updateProfile() {
 				String birthDateString = birthDateField.getText();
 				//TODO: input validation
 				currentProfile.setBirthDate(Date.valueOf(birthDateString));
 				// note: new qualifications may have been added to object
 				userService.updateProfile(currentProfile,
 						new AsyncCallback<Void>() {
 					
 							public void onFailure(Throwable caught) {
 								//TODO: error handling
 								errorLabel.setText(caught.getMessage());
 							}

 							public void onSuccess(Void empty) {
 								errorLabel.setText("SUCCESS! Updated profile with id: " + currentProfile.getId());
 							}
 						});
 			}
 			
 		}
 		
 		SaveProfileHandler saveProfileHandler = new SaveProfileHandler();
		saveProfileButton.addClickHandler(saveProfileHandler);
		
		
		// ADD QUALIFICATION POPUP
		final DialogBox addQualificationBox = new DialogBox();
		addQualificationBox.setText("Add Qualification");
		addQualificationBox.setAnimationEnabled(true);
		
		final Button addQualificationButton = new Button("Add");
		addQualificationButton.getElement().setId("addQualificationButton");
		
		final Label addQualificationLabel = new Label("Qualification:");
		final TextBox addQualificationField = new TextBox();
		VerticalPanel addQualificationBoxPanel = new VerticalPanel();
		addQualificationBoxPanel.addStyleName("addQualificationBoxPanel");
		addQualificationBoxPanel.add(addQualificationLabel);
		addQualificationBoxPanel.add(addQualificationField);
		addQualificationBoxPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		addQualificationBoxPanel.add(addQualificationButton);
		addQualificationBox.setWidget(addQualificationBoxPanel);

		class AddQualificationHandler implements ClickHandler {
 			public void onClick(ClickEvent event) {
 				addQualification();
 			}

 			private void addQualification() {
 				String qualification = addQualificationField.getText();
 				currentProfile.addQualification(qualification);
 				updateQualificationGrid(qualification);
 				addQualificationBox.hide();
 			}
 			
 			private void updateQualificationGrid(String qualification) {
 				int rowCount = qualificationGrid.getRowCount();
 				qualificationGrid.resizeRows(rowCount + 1);
 				qualificationGrid.setText(rowCount, 0, "Qualification: ");
 				qualificationGrid.setText(rowCount, 1, qualification);
 				
 			}
 			
 		}
		
		AddQualificationHandler addQualificationHandler = new AddQualificationHandler();
		addQualificationButton.addClickHandler(addQualificationHandler);
		
		class ShowAddQualificationHandler implements ClickHandler {
 			public void onClick(ClickEvent event) {
 				showAddQualification();
 			}

 			private void showAddQualification() {
 				addQualificationBox.setText("Add Qualification");
				addQualificationBox.center();
				addQualificationButton.setFocus(true);
 			}
 			
 		}
 		
		ShowAddQualificationHandler showAddQualificationHandler = new ShowAddQualificationHandler();
		showAddQualificationButton.addClickHandler(showAddQualificationHandler);
		
	}
	
	private void addProjectInformation() {
		final Label projectsLabel = new Label("Projects:");
	    projectPanel.add(projectsLabel);
	}
	
}
