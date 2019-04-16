package cs3500.animator.provider.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the implementation of the option popup panels.
 * <p>
 *   The option popup panels have the following variants: create shapes, remove shapes, add
 *   keyframe, and remove keyframe.
 *   The create shape variant has the options to enter for: name, x, y, w, h, r, g, b (the name
 *   of the shape, and all properities)
 *   The remove shape variant has the options to enter for: name (the shape to remove)
 *   The add keyframe has the options: name, t, x, y, w, h, r, g, b (the shape to add to, time
 *   stamp, and all shape properties)
 *   the remove keyframe has the options: name, t (the shape to remove the key frame from and the
 *   timestamp)
 * </p>
 */
public class PopUpOptionPanel extends JButton implements ActionListener {
  private String title;
  public String name;
  public int t;
  public int x;
  public int y;
  public int w;
  public int h;
  public int r;
  public int g;
  public int b;

  /**
   * Initialize the popup to a certain type.
   * @param title the title of the popup
   */
  public PopUpOptionPanel(String title) {
    this.title = title;

    switch (title) {
      case "Add Rectangle":
        this.setActionCommand("rectangle");
        break;
      case "Add Ellipse":
        this.setActionCommand("ellipse");
        break;
      case "Remove Shape":
        this.setActionCommand("removeShape");
        break;
      case "Add Keyframe":
        this.setActionCommand("addKeyFrame");
        break;
      case "Remove Keyframe":
        this.setActionCommand("removeKeyFrame");
        break;
      default:
        break;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JPanel popUp = new JPanel();
    popUp.setLayout(new BoxLayout(popUp, BoxLayout.Y_AXIS));

    JPanel name = new JPanel(new FlowLayout());
    name.add(new JLabel("Name", SwingConstants.RIGHT));
    JTextField nameText = new JTextField(10);
    name.add(nameText);

    JPanel t = new JPanel(new FlowLayout());
    t.add(new JLabel("T", SwingConstants.RIGHT));
    JTextField tText = new JTextField(10);
    t.add(tText);

    JPanel x = new JPanel(new FlowLayout());
    x.add(new JLabel("X", SwingConstants.RIGHT));
    JTextField xText = new JTextField(10);
    x.add(xText);

    JPanel y = new JPanel(new FlowLayout());
    y.add(new JLabel("Y", SwingConstants.RIGHT));
    JTextField yText = new JTextField(10);
    y.add(yText);

    JPanel w = new JPanel(new FlowLayout());
    w.add(new JLabel("W", SwingConstants.RIGHT));
    JTextField wText = new JTextField(10);
    w.add(wText);

    JPanel h = new JPanel(new FlowLayout());
    h.add(new JLabel("H", SwingConstants.RIGHT));
    JTextField hText = new JTextField(10);
    h.add(hText);

    JPanel r = new JPanel(new FlowLayout());
    r.add(new JLabel("R", SwingConstants.RIGHT));
    JTextField rText = new JTextField(10);
    r.add(rText);


    JPanel g = new JPanel(new FlowLayout());
    g.add(new JLabel("G", SwingConstants.RIGHT));
    JTextField gText = new JTextField(10);
    g.add(gText);


    JPanel b = new JPanel(new FlowLayout());
    b.add(new JLabel("B", SwingConstants.RIGHT));
    JTextField bText = new JTextField(10);
    b.add(bText);

    popUp.add(name);
    if (this.title.equals("Add Keyframe") || this.title.equals("Remove Keyframe")) {
      popUp.add(t);
    }

    if (this.title.equals("Add Rectangle") || this.title.equals("Add Ellipse")
        || this.title.equals("Add Keyframe")) {
      popUp.add(x);
      popUp.add(y);
      popUp.add(w);
      popUp.add(h);
      popUp.add(r);
      popUp.add(g);
      popUp.add(b);
    }

    int result = JOptionPane.showConfirmDialog(
        null, popUp, this.title, JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
      this.name = nameText.getText();
      if (!tText.getText().equals("")) {
        this.t = Integer.parseInt(tText.getText());
      }

      if (!xText.getText().equals("")) {
        this.x = Integer.parseInt(xText.getText());
      }

      if (!yText.getText().equals("")) {
        this.y = Integer.parseInt(yText.getText());
      }

      if (!wText.getText().equals("")) {
        this.w = Integer.parseInt(wText.getText());
      }

      if (!hText.getText().equals("")) {
        this.h = Integer.parseInt(hText.getText());
      }

      if (!rText.getText().equals("")) {
        this.r = Integer.parseInt(rText.getText());
      }

      if (!gText.getText().equals("")) {
        this.g = Integer.parseInt(gText.getText());
      }

      if (!bText.getText().equals("")) {
        this.b = Integer.parseInt(bText.getText());
      }

      this.doClick();
    }
  }
}
