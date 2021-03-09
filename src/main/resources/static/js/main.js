$(document).ready(function (){
    // For profile page
    $("#change_pass_form").hide()
    $("#error_message").hide()
    $("#change_pass_button").click(()=>{
        $("#change_pass_form").slideToggle(1000)
        $("#change_pass_button").slideUp();
    })
    $("#change_pass").keyup(check_passwords)
    $("#change_pass2").keyup(check_passwords)
    function check_passwords(){
        let pass1 = $("#change_pass").val()
        let pass2 = $("#change_pass2").val()
        let notEqualCheck = false
        let lengthCheck = false
        let error_message = ""

        if (pass1 != pass2){
            error_message = "Entered passwords different!"
            notEqualCheck = true
        } else {
            notEqualCheck = false
        }
        if (pass1.length <3 || pass1.length>30){
            error_message = "Password should be between 3 and 30 charachters!"
            lengthCheck = true
        } else {
            lengthCheck = false
        }
        if (lengthCheck == false && notEqualCheck == false){
            $("#submit_button").prop('disabled', false);
            $("#submit_button").addClass("button-primary")
            $("#submit_button").removeClass("button")
            $("#error_message").hide()
        } else {
            $("#submit_button").prop('disabled', true);
            $("#submit_button").removeClass("button-primary")
            $("#submit_button").addClass("button")
            $("#error_message").show()
            $("#error_message").text(error_message)
        }
    }

    // QUESTION FORM SHOW FUNCS
    $("#add_question_form").hide()
    $("#add_blank_form").hide()
    $("#update_question_form").hide()
    $("#delete_question_form").hide()

    $("#add_question").click(()=>{
        $("#add_question_form").slideToggle("slow");
    })
    $("#add_blank").click(()=>{
        $("#add_blank_form").slideToggle("slow");
    })
    $("#update_question").click(()=>{
        $("#update_question_form").slideToggle("slow");
    })
    $("#delete_question").click(()=>{
        $("#delete_question_form").slideToggle("slow");
    })

    // AUTHORITY FORM SHOW FUNCS

    $("#add_authority_form").hide()
    $("#delete_authority_form").hide()

    $("#add_authority").click(()=>{
        $("#add_authority_form").slideToggle("slow");
    })
    $("#remove_authority").click(()=>{
        $("#delete_authority_form").slideToggle("slow");
    })

    // ROLE FORM SHOW FUNCS

    $("#add_role_form").hide()
    $("#remove_role_form").hide()
    $("#edit_role_form").hide()

    $("#add_role").click(()=>{
        $("#add_role_form").slideToggle("slow");
    })
    $("#remove_role").click(()=>{
        $("#remove_role_form").slideToggle("slow");
    })
    $("#edit_role").click(()=>{
        $("#edit_role_form").slideToggle("slow");
    })

    let blank_buttons = document.querySelectorAll('.blank_container')

    blank_buttons.forEach(button => {
        button.addEventListener("click", (event)=>{
            let id = event.target.dataset.id

            window.location.href = `http://localhost:8080/quiz/${id}`
        })
    })

    let passed_blanks = document.querySelectorAll('.passed')

    passed_blanks.forEach(button => {
        button.addEventListener("click", (event)=>{
            let id = event.target.dataset.id

            window.location.href = `http://localhost:8080/quiz/passed/${id}`
        })
    })
})