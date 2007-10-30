function submitButton(formName,formAction)
{
	var theForm = document.getElementById(formName);
	theForm.action=formAction;
	theForm.submit();
}