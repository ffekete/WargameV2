package com.blacksoft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blacksoft.build.UserAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorsAction;
import com.blacksoft.dungeon.actions.ui.UpgradeIndicatorUpdater;
import com.blacksoft.screen.action.AddActorAction;
import com.blacksoft.screen.action.MovePlaceableActorToMouseAction;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;
import com.blacksoft.ui.DynamicLabel;
import com.blacksoft.ui.IntAction;
import com.blacksoft.ui.action.FollowCreatureAction;

import static com.blacksoft.state.Config.SCREEN_HEIGHT;
import static com.blacksoft.state.Config.SCREEN_WIDTH;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class UIFactory {

    public static final UIFactory I = new UIFactory();

    private BitmapFont bitmapFont12;
    private BitmapFont bitmapFont25;
    private BitmapFont bitmapFont30;
    private Label.LabelStyle labelStyle14;
    private Label.LabelStyle labelStyle22;
    private Label.LabelStyle labelStyle30;

    public UIFactory() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/orange_kid.ttf"));

        bitmapFont12 = getFont(generator, 14);
        labelStyle14 = new Label.LabelStyle();
        labelStyle14.font = bitmapFont12;

        bitmapFont30 = getFont(generator, 30);
        labelStyle30 = new Label.LabelStyle();
        labelStyle30.font = bitmapFont30;

        bitmapFont25 = getFont(generator, 22);
        labelStyle22 = new Label.LabelStyle();
        labelStyle22.font = bitmapFont25;

        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    private BitmapFont getFont(FreeTypeFontGenerator generator,
                               int size) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont bitmapFont = generator.generateFont(parameter); // font size 12 pixels
        return bitmapFont;
    }

    public Label getLabel14(String text) {
        return new Label(text, labelStyle14);
    }

    public Label addMovingLabel(String text) {

        SequenceAction sequenceAction = new SequenceAction();

        Label label = new Label(text, labelStyle30);
        label.setColor(Color.GREEN);
        label.setPosition(-50, SCREEN_HEIGHT / 4f);

        sequenceAction.addAction(new DelayAction(0.5f));

        MoveToAction moveToAction2 = getMoveToAction(label, Config.SCREEN_WIDTH / 4f - 2, Config.SCREEN_HEIGHT / 4f);
        moveToAction2.setDuration(0.15f);
        sequenceAction.addAction(moveToAction2);

        MoveToAction moveToAction3 = getMoveToAction(label, Config.SCREEN_WIDTH / 5f - 2, Config.SCREEN_HEIGHT / 4f);
        moveToAction3.setDuration(2f);
        sequenceAction.addAction(moveToAction3);

        MoveToAction moveToAction = getMoveToAction(label, SCREEN_WIDTH + 50, Config.SCREEN_HEIGHT / 4f);
        moveToAction.setDuration(0.15f);
        sequenceAction.addAction(moveToAction);

        RemoveActorAction removeAction = new RemoveActorAction();
        removeAction.setTarget(label);
        sequenceAction.addAction(removeAction);

        label.addAction(sequenceAction);

        return label;
    }

    public Label addMovingLabelShadow(String text) {

        SequenceAction sequenceAction = new SequenceAction();

        Label label = new Label(text, labelStyle30);
        label.setColor(Color.GRAY);
        label.setPosition(SCREEN_WIDTH / 2f - 60, SCREEN_HEIGHT / 4f - 2);

        sequenceAction.addAction(new DelayAction(0.5f));

        MoveToAction moveToAction = getMoveToAction(label, Config.SCREEN_WIDTH / 4f, Config.SCREEN_HEIGHT / 4f - 2);
        moveToAction.setDuration(0.15f);
        sequenceAction.addAction(moveToAction);

        MoveToAction moveToAction3 = getMoveToAction(label, Config.SCREEN_WIDTH / 5f, Config.SCREEN_HEIGHT / 4f - 2);
        moveToAction3.setDuration(2f);
        sequenceAction.addAction(moveToAction3);

        MoveToAction moveToAction2 = getMoveToAction(label, -50, Config.SCREEN_HEIGHT / 4f - 2);
        moveToAction2.setDuration(0.15f);
        sequenceAction.addAction(moveToAction2);

        RemoveActorAction removeAction = new RemoveActorAction();
        removeAction.setTarget(label);
        sequenceAction.addAction(removeAction);

        label.addAction(sequenceAction);

        return label;
    }

    private MoveToAction getMoveToAction(Label label,
                                         float tx,
                                         float ty) {
        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setTarget(label);
        moveToAction.setPosition(tx, ty);
        return moveToAction;
    }

    public Group getStatusBar() {

        Group horizontalGroup = new Group();

        UIState.statusBar = horizontalGroup;

        Table table = new Table();

        Label progressLabel = new Label(Integer.toString(GameState.loopProgress), labelStyle14);
        UIState.progressLabel = progressLabel;
        table.add(new Label("Progress", labelStyle14)).size(50);
        table.add(progressLabel).size(30).left();
        updateLabelAmount(0, GameState.loopProgress, progressLabel, "%s", null);

        table.add(new Label("Gold", labelStyle14)).size(30);
        Label goldLabel = new Label("", labelStyle14);
        UIState.goldLabel = goldLabel;
        table.add(goldLabel).size(60).left();
        updateLabelAmount(0, GameState.gold, goldLabel, "%s/%s", GameState.maxGoldCapacity);

        Label ironLabel = new Label("", labelStyle14);
        UIState.ironLabel = ironLabel;
        table.add(new Label("Iron", labelStyle14)).size(30);
        table.add(ironLabel).size(60).left();
        updateLabelAmount(0, GameState.iron, ironLabel, "%s/%s", GameState.maxIronCapacity);

        Label gemLabel = new Label("", labelStyle14);
        UIState.gemLabel = gemLabel;
        table.add(new Label("Gems", labelStyle14)).size(30);
        table.add(gemLabel).size(60).left();
        updateLabelAmount(0, GameState.gems, gemLabel, "%s/%s", GameState.maxGemsCapacity);

        horizontalGroup.addActor(new Image(new Texture(Gdx.files.internal("ui/StatusBar.png"))));
        horizontalGroup.addActor(table);


        table.setFillParent(true);
        horizontalGroup.setPosition(0, SCREEN_HEIGHT / 2 - 60);
        horizontalGroup.setSize(480, 32);

        return horizontalGroup;
    }

    public void createLockImages() {
        Image openLockImage = new Image(new Texture(Gdx.files.internal("ui/OpenLock.png")));
        Image closedLockImage = new Image(new Texture(Gdx.files.internal("ui/ClosedLock.png")));

        openLockImage.setVisible(false);
        closedLockImage.setVisible(false);

        openLockImage.setColor(1, 1, 1, 0.4f);
        closedLockImage.setColor(1, 1, 1, 0.4f);

        UIState.openLockImage = openLockImage;
        UIState.closedLockImage = closedLockImage;

        GameState.uiStage.addActor(openLockImage);
        GameState.uiStage.addActor(closedLockImage);
    }

    public Label createFloatingLabel(int newAmount,
                                     int x,
                                     int y) {
        Label label = new Label(Integer.toString(newAmount), labelStyle14);

        label.setPosition(x + 2, y);
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.moveTo(x + 2, y + 10, 0.5f));
        sequenceAction.addAction(Actions.removeActor());

        label.addAction(sequenceAction);

        GameState.uiStage.addActor(label);

        return label;
    }

    public DynamicLabel getFpsIndicator() {
        DynamicLabel fpsIndicator = new DynamicLabel(labelStyle14, () -> Integer.toString(Gdx.graphics.getFramesPerSecond()));
        fpsIndicator.setPosition(0, 10);
        return fpsIndicator;
    }

    public Group getActionsGroup() {

        Group group = new Group();
        group.setDebug(true);

        group.addActor(new Image(new Texture(Gdx.files.internal("ui/ActionPanel.png"))));

        HorizontalGroup horizontalGroup = new HorizontalGroup();
        UIState.actionsGroup = horizontalGroup;

        for (AbstractAction action : GameState.actions) {
            addAction(horizontalGroup, action);
        }

        group.setPosition(482, 0);
        horizontalGroup.setPosition(6, 128);
        horizontalGroup.setScale(2f);

        group.addActor(horizontalGroup);

        Container<DynamicLabel> descriptionGroup = new Container<>();
        descriptionGroup.setPosition(10, 100);
        descriptionGroup.setFillParent(true);
        descriptionGroup.top().left();

        group.addActor(descriptionGroup);

        DynamicLabel descriptionLabel = new DynamicLabel(labelStyle14, () -> GameState.highlightedAction != null ? getDescription() : "");
        descriptionGroup.setActor(descriptionLabel);

        return group;
    }

    public Group getCreatureListPanel() {

        Group group = new Group();

        group.addActor(new Image(new Texture(Gdx.files.internal("ui/CreatureListPanel.png"))));

        group.setPosition(482, 156);
        group.setSize(180, 200);

        Table table = new Table();
        table.pad(15, 15, 15, 15);
        table.setFillParent(true);
        table.top().left();

        ScrollPane scrollPane = new ScrollPane(table);
        group.addActor(scrollPane);
        scrollPane.setFillParent(true);

        UIState.creatureListTable = table;

        return group;
    }

    public void addCreatureListEntry(Creature creature) {
        AnimatedImage animatedImage = new AnimatedImage(creature.getAnimation());
        DynamicLabel hpLabel = new DynamicLabel(labelStyle14, () -> Integer.toString(creature.getHp()));
        DynamicLabel maxHpLabel = new DynamicLabel(labelStyle14, () -> Integer.toString(creature.getMaxHp()));
        Label hpDescrLabel = new Label("hp:", labelStyle14);
        Label separator = new Label("/", labelStyle14);

        Table table = new Table();

        table.add(animatedImage).size(16).left().pad(3, 5, 3, 15);
        table.add(hpDescrLabel).left().pad(3, 0, 3, 0).size(16);
        table.add(hpLabel).left().pad(3, 2.5f, 3, 0);
        table.add(separator).left().pad(3, 2.5f, 3, 0);
        table.add(maxHpLabel).left().pad(3, 2.5f, 3, 0).size(16).expandX().fillX();

        UIState.creatureListTable.add(table).fillX().expandX();

        GameState.creatureListEntries.put(creature, table);

        FollowCreatureAction followCreatureAction = new FollowCreatureAction(creature, () -> null);

        GameState.followCreatureAction = followCreatureAction;

        table.setBackground(UIState.selectionBackground);

        table.addListener(new InputListener() {

            @Override
            public void enter(InputEvent event,
                              float x,
                              float y,
                              int pointer,
                              Actor fromActor) {

                table.getChild(0).setScale(1.5f);
                table.setBackground(UIState.selectionBackgroundHighlighted);
                table.setPosition(table.getX() - 1, table.getY());

                GameState.creatureListEntries.entrySet().stream()
                        .filter(entry -> entry.getValue() == table)
                        .map(entry -> entry.getKey())
                        .findFirst()
                        .ifPresent(creature2 -> {
                            UIState.selectionMarker.setPosition(creature2.getX(), creature2.getY());
                            UIState.selectionMarker.setVisible(true);
                            followCreatureAction.setCreature(creature2);
                            followCreatureAction.setTarget(() -> UIState.selectionMarker);
                            creature2.addAction(followCreatureAction);
                        });
            }

            @Override
            public void exit(InputEvent event,
                             float x,
                             float y,
                             int pointer,
                             Actor toActor) {
                UIState.selectionMarker.setVisible(false);

                table.getChild(0).setScale(1f);
                table.setBackground(UIState.selectionBackground);
                table.setPosition(table.getX() + 1, table.getY());

                GameState.creatureListEntries.entrySet().stream()
                        .filter(entry -> entry.getValue() == table)
                        .map(entry -> entry.getKey())
                        .findFirst()
                        .ifPresent(creature2 -> {
                            creature2.removeAction(followCreatureAction);
                        });
            }
        });

        UIState.creatureListTable.row();
    }

    public void createSelectionMarker() {
        TextureRegion[] textureRegion = TextureRegion.split(new Texture(Gdx.files.internal("ui/SelectionMarker.png")), TEXTURE_SIZE, TEXTURE_SIZE)[0];
        Animation<TextureRegion> animation = new Animation<TextureRegion>(0.3f, textureRegion);
        AnimatedImage animatedImage = new AnimatedImage(animation);

        UIState.selectionMarker = animatedImage;

        UIState.selectionMarker.setVisible(false);

        GameState.uiStage.addActor(animatedImage);
    }

    public void addAction(Group horizontalGroup,
                          AbstractAction action) {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.imageUp = new TextureRegionDrawable(action.getTexture());
        imageButtonStyle.imageDown = new TextureRegionDrawable(action.getTexture());
        ImageButton image = new ImageButton(imageButtonStyle);
        image.pad(0.5f);

        image.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event,
                              float x,
                              float y,
                              int pointer,
                              Actor fromActor) {
                if (GameState.userAction != UserAction.Place) {
                    image.setY(image.getY() + 2);
                    GameState.highlightedAction = action;
                    GameState.highlightedActionImage = image;
                }
            }

            @Override
            public void exit(InputEvent event,
                             float x,
                             float y,
                             int pointer,
                             Actor toActor) {
                if (GameState.userAction != UserAction.Place) {
                    image.setY(image.getY() - 2);
                    GameState.highlightedAction = null;
                    GameState.highlightedActionImage = null;
                }
            }

            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                if (GameState.userAction == UserAction.Place) {
                    // already placing, cannot place until the current item is placed
                    return true;
                }

                GameState.selectedActionImage = image;

                GameState.userAction = UserAction.Place;
                CleanIndicatorsAction.cleanAll(GameState.dungeon);

                GameState.selectedAction = action;

                UpgradeIndicatorUpdater.update(GameState.dungeon);

                ParallelAction moveAndScaleAction = new ParallelAction();

                RemoveActorAction removeActorAction = new RemoveActorAction();
                image.setTransform(true);
                removeActorAction.setTarget(image);

                SequenceAction sequenceAction = new SequenceAction();
                moveAndScaleAction.addAction(Actions.scaleTo(2f, 2f, 0.2f));
                moveAndScaleAction.addAction(Actions.moveTo(image.getX(), image.getY() + 40, 0.2f));

                sequenceAction.addAction(moveAndScaleAction);
                sequenceAction.addAction(Actions.visible(false));
                sequenceAction.addAction(new AddActorAction(image));
                sequenceAction.addAction(Actions.scaleTo(1f, 1f));
                sequenceAction.addAction(Actions.visible(true));
                sequenceAction.addAction(new MovePlaceableActorToMouseAction(image));

                image.addAction(sequenceAction);

                return true;
            }
        });

        image.setTransform(true);
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.scaleTo(2f, 2f));
        sequenceAction.addAction(Actions.scaleTo(1f, 1f, 0.1f));

        image.addAction(sequenceAction);

        horizontalGroup.addActor(image);
    }

    private String getDescription() {
        return String.format("%s\n%s", GameState.highlightedAction.getTitle(), GameState.highlightedAction.getDescription());
    }

    public void updateLabelAmount(int old,
                                  int newValue,
                                  Label label,
                                  String template,
                                  Integer other) {
        if (label != null) {
            com.blacksoft.ui.IntAction intAction = new IntAction(old, newValue, 0.5f, label, template, other);
            GameState.uiStage.addAction(intAction);
        }
    }

}
