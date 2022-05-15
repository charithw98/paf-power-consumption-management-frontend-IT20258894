//Hide the alerts on page load
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide();
});
// SAVE
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();
	 
	// Form validation
	var status = validatePowerForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	 }	
	// If valid
	var type = ($("#hidPowerIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
		{
			url : "PowerAPI",
			type : type,
			data : $("#formPower").serialize(),
			dataType : "text",
			complete : function(response, status)
			{
				onPowerSaveComplete(response.responseText, status);
			}
		});
});

// UPDATE
$(document).on("click", ".btnUpdate", function(event)
{		
	
	 $("#hidPowerIDSave").val($(this).data("id"));
	 $("#usage").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#unitType").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#description").val($(this).closest("tr").find('td:eq(2)').text());
	
});

//DELETE
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
		{
			url : "powerAPI",
			type : "DELETE",
			data : "ID=" + $(this).data("id"),
			dataType : "text",
			complete : function(response, status)
			{
				onPowerDeleteComplete(response.responseText, status);
			}
		});
});

// CLIENT-MODEL
function validatePowerForm()
{
	// CODE
	if ($("#ID").val().trim() == "")
	 {
		return "Insert ID.";
	 }
	// NAME
	if ($("#usage").val().trim() == "")
	 {
		return "Insert usage.";
	 }
	//PRICE
	if ($("#unitType").val().trim() == "")
	 {
		return "Insert unit type.";
	 }
	// is numerical value
	var tmpUsage = $("#usage").val().trim();
		if (!$.isNumeric(tmpUsage))
	 {
			return "Insert a numerical value for usage.";
	 }
	// convert to decimal price
	 $("#usage").val(parseFloat(tmpusage).toFixed(2));
	// DESCRIPTION
	if ($("#description").val().trim() == "")
	 {
		return "Insert  Description.";
	 }
	return true;
}
function onPowerSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divPowereGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	
	else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidIDSave").val("");
	$("#formPower")[0].reset();
}
function onPowerDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divPowerGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}