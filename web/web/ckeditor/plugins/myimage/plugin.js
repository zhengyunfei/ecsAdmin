/**
 * Title:CKEditor插件示范
 * Author:铁木箱子(http://www.mzone.cc)
 * Date:2010-08-02
 */
CKEDITOR.plugins.add('myimage', {
    lang:['zh-cn','en'],
    requires: ['dialog'],
    init: function(a){
        var b = a.addCommand('myimage', new CKEDITOR.dialogCommand('myimage'));
        a.ui.addButton('myimage', {
            label: a.lang.tbTip,
            command: 'myimage',
            icon: this.path + 'images/placeholder.png'
        });
        CKEDITOR.dialog.add('myimage', this.path + 'dialogs/myimage');
    }
});