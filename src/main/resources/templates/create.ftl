<#import "/spring.ftl" as spring />

<form action="save.html" method="post">

    <@spring.bind "userModel" />

    Name <@spring.formInput "userModel.name"/> <span style="color: red"><@spring.showErrors "<br>"/></span>

    <input type="submit" value="Submit" />

</form>