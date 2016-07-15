CKEDITOR.plugins.add(
	    "idcard",
	    {
	        requires:["dialog"],
	        init:function (a)
	        {
	            a.addCommand("idcard", new CKEDITOR.dialogCommand("idcard"));
	            a.ui.addButton(
	                "Idcard",
	                {
	                    label:"身份证",
	                    command:"idcard"
	                });
	            CKEDITOR.dialog.add("idcard", this.path + "dialogs/idcard.js");
	        }
	    }
	);