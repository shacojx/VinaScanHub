/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaySig;

/**
 *
 * @author Shaco JX
 */
public class CVE_2017_9248 {

    private static String[] arrSigCVE;
    private static String[] arrPayCVE;

    public CVE_2017_9248() {
        arrPayCVE = new String[]{
            "Telerik.Web.UI.DialogHandler.aspx",
            "DesktopModules/Admin/RadEditorProvider/DialogHandler.aspx",
            "Providers/HtmlEditorProviders/Telerik/Telerik.Web.UI.DialogHandler.aspx",
            "DesktopModules/Admin/RadEditorProvider/DialogHandler.aspx",
            "DesktopModule/UIQuestionControls/UIAskQuestion/Telerik.Web.UI.DialogHandler.aspx",
            "Modules/CMS/Telerik.Web.UI.DialogHandler.aspx",
            "Admin/ServerSide/Telerik.Web.UI.DialogHandler.aspx",
            "DesktopModules/TNComments/Telerik.Web.UI.DialogHandler.aspx",
            "Providers/HtmlEditorProviders/Telerik/Telerik.Web.UI.DialogHandler.aspx",
            "Providers/HtmlEditorProviders/Fck/fcklinkgallery.aspx"
        };

        arrSigCVE = new String[]{
            "Loading the dialog...",};
    }

    public String[] getArrSigCVE() {
        return arrSigCVE;
    }

    public String[] getArrPayCVE() {
        return arrPayCVE;
    }
}
