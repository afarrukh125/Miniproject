package me.afarrukh.miniproject.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.gfx.Text;
import me.afarrukh.miniproject.gfx.Visuals;
import me.afarrukh.miniproject.items.Item;

public class Inventory {

    private Manager manager;
    private boolean active = false;

    // *******************************************************************
    // Very specific coordinates
    private final int invWidth = 528;
    private final int invHeight = 424;
    private final int invX = 275;
    private final int invY = 140;

    private final int invListCenterY = invY + invHeight / 2;

    private int invImageX = 452;
    private int invImageY = 82;

    // Drawing on the character and stats
    private final int charImgX = invX + invWidth - 165;
    private final int charImgY = invY + 15;

    // *******************************************************************

    // Other variables
    private int selectedItem = 0;

    private final ArrayList<Item> inventoryItems;

    public Inventory(Manager manager) {
        this.manager = manager;
        inventoryItems = new ArrayList<>();
    }

    public void tick() {
        if (manager.getKeyManager().keyJustPressed(KeyEvent.VK_I))
            active = !active; // switches the status of the active status of the inventory

        if (manager.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE) && active) active = false;

        if (!active) return;

        if (manager.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) selectedItem--;
        if (manager.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) selectedItem++;

        if (selectedItem < 0) selectedItem = inventoryItems.size() - 1;
        else if (selectedItem >= inventoryItems.size()) selectedItem = 0;
    }

    public void render(Graphics g) {
        if (!active) return;

        g.drawImage(Visuals.inventoryUI, invX, invY, invWidth, invHeight, null);
        // Drawing character attributes and image onto the inventory menu
        int charImgHeight = 128;
        int charImgWidth = 128;
        g.drawImage(
                manager.getMap().getEntityManager().getPlayer().getStillAnim(),
                charImgX,
                charImgY,
                charImgWidth,
                charImgHeight,
                null);
        int charLabelY = charImgY + 168;
        int charLabelX = charImgX + 70;
        Text.drawString(
                g,
                manager.getMap().getEntityManager().getPlayer().getType(),
                charLabelX,
                charLabelY,
                true,
                Color.WHITE,
                Visuals.font28);

        // Now proceeding to draw inventory items

        int length = inventoryItems.size();
        // If there are no items in inventory we do not proceed with this menu
        if (length == 0) return;

        Item item = inventoryItems.get(selectedItem);

        for (int i = -2; i < 3; i++) {
            if (selectedItem + i < 0 || selectedItem + i >= length) // Checking bounds
            continue;
            int invImageHeight = 64;
            int invImageWidth = 64;
            int invImgCenterY = invListCenterY - 32;
            int invImgCenterX = invX + 20;
            int invListSpacing = 85;
            if (i == 0) {
                // Drawing both text and image for selected image
                int invListCenterX = invX + 220;
                Text.drawString(
                        g,
                        ">> " + inventoryItems.get(selectedItem + i).getName() + " <<",
                        invListCenterX,
                        invListCenterY + i * invListSpacing,
                        true,
                        Color.GREEN,
                        Visuals.font28);

                g.drawImage(
                        item.getTexture(),
                        invImgCenterX,
                        invImgCenterY + i * invListSpacing,
                        invImageWidth,
                        invImageHeight,
                        null);

            } else {
                g.drawImage(
                        inventoryItems.get(selectedItem + i).getTexture(),
                        invImgCenterX,
                        invImgCenterY + i * invListSpacing,
                        invImageWidth,
                        invImageHeight,
                        null);
            }
        }
        // Draws the number of instances of selected item
        int invCountY = 417;
        int invCountX = 495;
        Text.drawString(
                g, Integer.toString(item.getInstances()), invCountX, invCountY, true, Color.WHITE, Visuals.font28);
    }

    // Inventory methods here

    public void addItem(Item item) {
        for (Item i : inventoryItems) {
            // This increments the item count in the players inventory if they already have it
            if (i.getId() == item.getId()) {
                i.setInstances(i.getInstances() + item.getInstances());
                return;
            }
        }
        // If they already have it, add a new item
        inventoryItems.add(item);
    }

    // Getters and setters
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public boolean isActive() {
        return active;
    }
}
