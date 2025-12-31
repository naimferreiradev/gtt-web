package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);

    @Dado("que o usuario esteja no gtt dev")
    public void que_o_usuario_esteja_no_gtt_dev(){
        loginPage.acessarPagina();
    }

    @Quando("preenche email e senha")
    public void preenche_email_e_senha() throws InterruptedException {
        loginPage.preencherUsuario();
        loginPage.preencherSenha();
        loginPage.clicarEntrar();
    }

    @Quando("preenche email e senha invalido")
    public void preenche_email_e_senha_invalido() throws InterruptedException {
        loginPage.preencherUsuario();
        loginPage.preencherSenha();
        loginPage.clicarEntrar();
    }

    @Então("deve ser apresentada a mensagem de boas vindas")
    public void deve_ser_apresentada_a_mensagem_de_boas_vindas() {
        homePage.validarHomepageBoasvindas();
    }

    @Então("deve ser apresentada uma mensagem informando usuário e senha invalido")
    public void deve_ser_apresentada_uma_mensagem_informando_usuário_e_senha_invalido() {
        loginPage.alertaLogin();
    }

}