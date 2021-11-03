// 
// Decompiled by Procyon v0.5.36
// 
package common;

import base.DCanvas;
import means.DebugFrame;

public class GridTable extends GridLayout {

    private GridCell[][] s_grid_layout_array;
    private short s_grid_row;
    private short s_grid_col;
    private short s_top_x;
    private short s_top_y;
    private short s_grid_w;
    private short s_grid_h;
    private short s_cell_w;
    private short s_cell_h;
    private short[] s_row_percent;
    private short[] s_col_percent;

    public GridTable(final short x, final short y, final short w, final short h, final short row, final short col, final short[] percentRow, final short[] percentCol) {
        this.initGridData(x, y, w, h, row, col, percentRow, percentCol);
        this.initGridTable();
        int totlerow = 0;
        int totlelist = 0;
        for (int i = 0; i < this.s_row_percent.length; ++i) {
            totlerow += this.s_row_percent[i];
        }
        for (int i = 0; i < this.s_col_percent.length; ++i) {
            totlelist += this.s_col_percent[i];
        }
        if (totlerow > 100) {
            DebugFrame.getInstance().logIn("Warning!!! \u884c\u7684\u767e\u5206\u6bd4\u76f8\u52a0\u5927\u4e8e100");
        }
        if (totlelist > 100) {
            DebugFrame.getInstance().logIn("Warning!!! \u5217\u7684\u767e\u5206\u6bd4\u76f8\u52a0\u5927\u4e8e100");
        }
    }

    public void addGrid(final GridCell grid, final int row, final int col) {
        if (this.s_grid_layout_array != null) {
            if (this.s_grid_layout_array[row][col].getComposite() != null) {
                DebugFrame.getInstance().logIn("Warning!!! \u8be5Cell\u4e2d\u5df2\u7ecf\u5b58\u5728\u4e00\u4e2aTable,\u5c06\u88ab\u66ff\u6362\uff0c\u5982\u9700\u5d4c\u5957\uff0c\u8bf7\u8fed\u4ee3\u81f3\u6700\u5e95\u5c42\u8868\u683c\uff01");
            }
            this.s_grid_layout_array[row][col] = grid;
        }
    }

    public void removeGrid(final int row, final int col) {
        if (this.s_grid_layout_array != null) {
            if (this.s_grid_layout_array[row][col].getComposite() != null) {
                final short x = (short) (this.s_top_x + col * this.s_grid_w);
                final short y = (short) (this.s_top_y + row * this.s_grid_h);
                final short w = this.s_grid_w;
                final short h = this.s_grid_h;
                this.s_grid_layout_array[row][col] = new GridCell(x, y, w, h);
            } else {
                DebugFrame.getInstance().logIn("Warning!!! \u8be5Cell\u4e2d\u4e0d\u5b58\u5728Table\uff0c\u5ffd\u7565\u8be5\u5220\u9664\u64cd\u4f5c\uff01");
            }
        }
    }

    public int getCellIndex(int row, int col) {
        --row;
        --col;
        return row * (this.s_grid_col - 1) + row % this.s_grid_row + col % this.s_grid_col;
    }

    public GridCell getGrid(final int row, final int col) {
        return this.s_grid_layout_array[row][col];
    }

    public GridCell getCell(final int row, final int col) {
        return this.s_grid_layout_array[row - 1][col - 1];
    }

    public short[] getMultiCellRect(final int startRow, final int rowNum, final int startCol, final int colNum) {
        final short[] clip = {this.getCell(startRow, startCol).getCellX(), this.getCell(startRow, startCol).getCellY(), 0, 0};
        clip[2] = (short) (this.getCell(startRow, startCol + colNum - 1).getCellX() + this.getCell(startRow, startCol + colNum - 1).getCellW() - clip[0]);
        clip[3] = (short) (this.getCell(startRow + rowNum - 1, startCol).getCellY() + this.getCell(startRow + rowNum - 1, startCol).getCellH() - clip[1]);
        return clip;
    }

    public GridLayout getComponent(final int row, final int col) {
        return this.s_grid_layout_array[row][col];
    }

    private void initGridData(final short x, final short y, final short w, final short h, final short row, final short col, short[] percentRow, short[] percentCol) {
        this.s_grid_row = row;
        this.s_grid_col = col;
        this.s_top_x = x;
        this.s_top_y = y;
        this.s_grid_w = w;
        this.s_grid_h = h;
        this.s_cell_w = (short) (this.s_grid_w / this.s_grid_col);
        this.s_cell_h = (short) (this.s_grid_h / this.s_grid_row);
        if (percentRow == null) {
            percentRow = new short[this.s_grid_row];
            for (int r = 0; r < this.s_grid_row; ++r) {
                percentRow[r] = this.s_cell_h;
            }
        }
        if (percentCol == null) {
            percentCol = new short[this.s_grid_col];
            for (int r = 0; r < this.s_grid_col; ++r) {
                percentCol[r] = this.s_cell_w;
            }
        }
        this.s_row_percent = percentRow;
        this.s_col_percent = percentCol;
    }

    private void initGridTable() {
        this.s_grid_layout_array = new GridCell[this.s_grid_row][this.s_grid_col];
        short stop_buff_x = this.s_top_x;
        for (int rowIndex = 0; rowIndex < this.s_grid_row; ++rowIndex) {
            final short h = (short) (this.s_row_percent[rowIndex] * this.s_grid_h / 100);
            final short y = this.s_top_y;
            this.s_top_y += h;
            for (int colIndex = 0; colIndex < this.s_grid_col; ++colIndex) {
                final short w = (short) (this.s_col_percent[colIndex] * this.s_grid_w / 100);
                final short x = stop_buff_x;
                stop_buff_x += w;
                this.s_grid_layout_array[rowIndex][colIndex] = new GridCell(x, y, w, h);
                DCanvas.gameG.setColor(65298);
                if (Pram.BLN_SHOW_BORDER) {
                    DCanvas.gameG.setColor(65298);
                    DCanvas.gameG.drawRect((int) x, (int) y, (int) w, (int) h);
                }
            }
            stop_buff_x = this.s_top_x;
        }
    }

    public GridLayout getComposite() {
        return this;
    }
}
