/**
 *
 */
package net.java.dev.ims.books.dao;

import net.java.dev.ims.books.model.Label;

import java.util.List;


/**
 * @author Jeff.Yu
 *
 */
public interface LabelDAO {
	
    public Label createLabel(Label label);

    public void updateLabel(Label label);

    public void removeLabel(Label label);

    public List<Label> getLabels(long userID);

    public List<Label> getLabels();

    public Label getLabel(long labelID, long userID);

    public List<Label> getBlogsLabel(long blogID);
    
    public Label getLabel(String labelName, long userID);
    
}
