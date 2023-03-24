package com.cydeo.steps;

import com.cydeo.pages.UsersPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.*;
import io.cucumber.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class UsersStepDefs {

    UsersPage usersPage = new UsersPage();

    String email;

    String expectedStatus;

    @When("the user clicks Edit User button")
    public void the_user_clicks_edit_user_button() {
       usersPage.editUser.click();
       BrowserUtil.waitFor(2);
    }
    @When("the user changes user status {string} to {string}")
    public void the_user_changes_user_status_to(String ACTIVE, String INACTIVE) {

        Select select = new Select(usersPage.statusDropdown);
        select.selectByValue(INACTIVE);
        BrowserUtil.waitFor(2);
        //save the email of the user that we changed the status
        email = usersPage.email.getAttribute("value");
        System.out.println(email);
        expectedStatus=INACTIVE;
    }
    @When("the user clicks save changes button")
    public void the_user_clicks_save_changes_button() {
        BrowserUtil.waitFor(1);
        usersPage.saveChanges.click();

    }
    @Then("{string} message should appear")
    public void message_should_appear(String expectedMessage) {
        BrowserUtil.waitFor(1);
        String actualMessage = usersPage.toastMessage.getText();

        Assert.assertEquals(expectedMessage,actualMessage);
    }
    @Then("the users should see same status for related user in database")
    public void the_users_should_see_same_status_for_related_user_in_database() {
      //run query to get actual status
        String query = "select status from users where email='"+email+"'";
        DB_Util.runQuery(query);

        String actualStatus = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualStatus);

        Assert.assertEquals(actualStatus,expectedStatus);
    }

}
