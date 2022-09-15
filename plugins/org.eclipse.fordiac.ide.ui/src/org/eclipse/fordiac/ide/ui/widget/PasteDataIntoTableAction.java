package org.eclipse.fordiac.ide.ui.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.fordiac.ide.ui.FordiacMessages;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.edit.command.UpdateDataCommand;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.ui.action.IKeyAction;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

public class PasteDataIntoTableAction implements IKeyAction {

	@Override
	public void run(final NatTable natTable, final KeyEvent event) {
		final Clipboard clipboard = new Clipboard(Display.getCurrent());
		final Object clipboardContents = clipboard.getContents(TextTransfer.getInstance());
		clipboard.dispose();
		final String[][] content = parseContent(clipboardContents);

		final SelectionLayer selectionLayer = (SelectionLayer) natTable.getLayer().getUnderlyingLayerByPosition(0, 1)
				.getUnderlyingLayerByPosition(0, 0);
		final List<Rectangle> ranges = selectionLayer.getSelectionModel().getSelections().stream().distinct()
				.collect(Collectors.toList());

		if (ranges.size() != 1) {
			MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "", //$NON-NLS-1$
					FordiacMessages.NatTable_TEXT_Paste);
			return;
		}

		final int startingRow = ranges.get(0).y;
		final int startingColumn = ranges.get(0).x;
		for (final ILayerCell cell : selectionLayer.getSelectedCells()) {
			final int arrayRowIndex = cell.getRowIndex() - startingRow;
			final int arrayColumnIndex = cell.getColumnIndex() - startingColumn;

			if (arrayRowIndex < content.length && arrayColumnIndex < content[0].length) {
				selectionLayer.doCommand(new UpdateDataCommand(selectionLayer, cell.getColumnIndex(),
						cell.getRowIndex(), content[arrayRowIndex][arrayColumnIndex]));
			}
		}
	}

	private static String[][] parseContent(final Object contents) {
		final List<String[]> lines = new ArrayList<>();
		((String) contents).lines().forEach(s -> {
			final String[] st = s.split("\t", -1); //$NON-NLS-1$
			lines.add(st);
		});

		final String[][] content = new String[lines.size()][];
		for (int i = 0; i < lines.size(); i++) {
			content[i] = lines.get(i);
		}

		return content;
	}
}