package de.uni_mannheim.bwl.schader.odm.garedo.client;

import de.uni_mannheim.bwl.schader.odm.garedo.client.model.User;
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
	private final UserServiceAsync userService = GWT
			.create(UserService.class);

	private User currentUser;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		currentUser = null;
		loadLogin();
	}
	
	private void loadLogin() {
		
		final Button loginButton = new Button("Log In");
		final TextBox loginField = new TextBox();
		loginField.setText("");
		
		final Button signupButton = new Button("Sign Up");
		final TextBox signupField = new TextBox();
		signupField.setText("");
		
		final Label errorLabel = new Label();
		
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
						new AsyncCallback<User>() {
					
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

							public void onSuccess(User user) {
//								dialogBox.setText("Remote Procedure Call");
//								serverResponseLabel
//										.removeStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(user.getName());
//								dialogBox.center();
//								closeButton.setFocus(true);
								errorLabel.setText("SUCCESS! Logged on as " + user.getName());
								currentUser = user;
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
						new AsyncCallback<User>() {
					
							public void onFailure(Throwable caught) {
								errorLabel.setText(caught.getMessage());
							}

							public void onSuccess(User user) {
								errorLabel.setText("SUCCESS! Created user: " + user.getName());
								currentUser = user;
								loadDashboard();
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
	    final HorizontalPanel dashboardPanel = new HorizontalPanel();
	    RootPanel.get().add(dashboardPanel);
	    
	    final VerticalPanel profilePanel = new VerticalPanel();
	    final VerticalPanel projectPanel = new VerticalPanel();
	    dashboardPanel.add(profilePanel);
	    dashboardPanel.add(projectPanel);
	    
	    final Label profileLabel = new Label("Profile:");
	    profilePanel.add(profileLabel);
	    
	    final Label projectsLabel = new Label("Projects:");
	    projectPanel.add(projectsLabel);
	    
	    //RootPanel.get().add("User: " + currentUser.getName());
	}
	
}
