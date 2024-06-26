package gui.Utils;

import gui.FeedUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshTask implements ActionListener {
    private FeedUI feedUI;

    public RefreshTask(FeedUI feedUI) {
        this.feedUI = feedUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        feedUI.refresh();
    }
}

